package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.User;
import zlogger.logic.models.UserDetails;
import zlogger.logic.models.Wall;
import zlogger.logic.services.AuthenticationService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.BlogModel;
import zlogger.web.models.PasswordModel;
import zlogger.web.models.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserController {

    private static final Logger LOGGER = Logger.getGlobal();

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
    public ModelAndView getWall(@PathVariable("username") String username,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @CookieValue(value = "pageSize", defaultValue = "5") int pageSize,
                                Authentication authentication) {
        BlogModel blogModel = new BlogModel();
        blogModel.setBlogName(username + "\'s blog");
        Wall wall = userService.getWall(username);
        blogModel.setPosts(postService.list(username, page, pageSize));
        blogModel.setOwner(userService.getUserDetails(username));

        if (authenticationService.isOwner(authentication, wall)) {
            blogModel.setCanAddPost(true);
        } else {
            blogModel.setCanAddPost(false);
        }

        return new ModelAndView("blog", "blogModel", blogModel);
    }

    @RequestMapping(value = "/user/profile",
            method = RequestMethod.GET)
    public ModelAndView showProfile(Authentication authentication) {
        UserModel userModel = new UserModel();
        userModel.setUsername(authentication.getName());
        userModel.setDetails(userService.getUserDetails(authentication.getName()));
        return new ModelAndView("profile", "userModel", userModel);
    }

    @RequestMapping(value = "/user/changedetails",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public void changeUserDetails(@RequestBody UserDetails details,
                                          Authentication authentication) {
        details.setUser(new User(authentication.getName(), null));
        userService.updateUserDetails(details);
    }

    @RequestMapping(value = "/user/changepassword",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> changePassword(@RequestBody PasswordModel passwordModel,
                                                 Authentication authentication) {
        User user = userService.get(authentication.getName());

        if (passwordModel.getNewPassword().length() <
                authenticationService.getMinPasswordLength()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (passwordEncoder.matches(passwordModel.getCurrentPassword(), user.getPassword())) {
            String hashedPassword = passwordEncoder.encode(passwordModel.getNewPassword());
            user.setPassword(hashedPassword);
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/user/changeavatar",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> changeUserAvatar(@RequestParam("file") MultipartFile avatar,
                                                    HttpServletRequest request,
                                                    Authentication authentication) {
        if (!avatar.isEmpty()) {
            if ("image/png".equals(avatar.getContentType())) {
                try {
                    byte[] bytes = avatar.getBytes();
                    String pathToImg = request.getServletContext().getRealPath("/resources/img");
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(
                                            pathToImg + "/" + authentication.getName() + ".png")));
                    stream.write(bytes);
                    stream.close();
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "IO error when changing avatar", e);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
