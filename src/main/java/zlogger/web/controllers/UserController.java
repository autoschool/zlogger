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
import zlogger.web.models.Message;
import zlogger.web.models.PasswordModel;
import zlogger.web.models.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON)
    public ModelAndView changeUserDetails(@RequestBody UserDetails details,
                                          HttpServletResponse response,
                                          Authentication authentication) {
        details.setUser(new User(authentication.getName(), null));
        userService.updateUserDetails(details);
        UserModel userModel = new UserModel();
        userModel.setUsername(authentication.getName());
        userModel.setDetails(userService.getUserDetails(authentication.getName()));
        return new ModelAndView("profile", "userModel", userModel);
    }

    @RequestMapping(value = "/user/changepassword",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON)
    public void changePassword(@RequestBody PasswordModel passwordModel,
                               HttpServletResponse response,
                               Authentication authentication) throws IOException {
        User user = userService.get(authentication.getName());
        String hashedPassword = passwordEncoder.encode(passwordModel.getOldPassword());
        if (user.getPassword().equals(hashedPassword)) {
            hashedPassword = passwordEncoder.encode(passwordModel.getNewPassword());
            user.setPassword(hashedPassword);
            userService.update(user);
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @RequestMapping(value = "/user/changeavatar", method = RequestMethod.POST)
    public ResponseEntity<Message> changeUserAvatar(@RequestParam("file") MultipartFile avatar,
                                                    HttpServletResponse response,
                                                    HttpServletRequest request,
                                                    Authentication authentication) {
        if (!avatar.isEmpty()) {
            if (avatar.getContentType().equals("image/png")) {
                try {
                    byte[] bytes = avatar.getBytes();
                    String pathToImg = request.getServletContext().getRealPath("/resources/img");
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(
                                            pathToImg + "/" + authentication.getName() + ".png")));
                    stream.write(bytes);
                    stream.close();
                    return new ResponseEntity<>(new Message("Successfully changed avatar"), HttpStatus.OK);
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "IO error when changing avatar", e);
                    return new ResponseEntity<>(new Message("Error when saving on disk"), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(new Message("Avatar must be .png file"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new Message("Can't save empty file"), HttpStatus.BAD_REQUEST);
        }
    }

}
