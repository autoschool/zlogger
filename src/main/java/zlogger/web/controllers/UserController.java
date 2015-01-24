package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.Wall;
import zlogger.logic.services.AuthenticationService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.BlogModel;

import javax.ws.rs.GET;

@Controller
public class UserController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    @GET
    @RequestMapping("/blog/{username}")
    public ModelAndView getWall(@PathVariable("username") String username,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @CookieValue(value = "pageSize", defaultValue = "5") int pageSize,
                                Authentication authentication) {
        BlogModel blogModel = new BlogModel();
        blogModel.setBlogName(username + "\'s blog");
        Wall wall = userService.getWall(username);
        blogModel.setPosts(postService.list(username, page, pageSize));

        if (authenticationService.isOwner(authentication, wall)) {
            blogModel.setCanAddPost(true);
        } else {
            blogModel.setCanAddPost(false);
        }
        return new ModelAndView("blog", "blogModel", blogModel);
    }

}
