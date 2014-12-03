package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.AuthenticationService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.BlogModel;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping("/blog/{username}")
    public ModelAndView getWall(@PathVariable("username") String username, Authentication authentication) {
        BlogModel blogModel = new BlogModel();
        blogModel.setBlogName(username + "\'s blog");
        blogModel.setUrlLoadPost("/blog/" + username + "/posts");
        Wall wall = userService.getWall(new User(username, null));

        if (authenticationService.isOwner(authentication, wall)) {
            blogModel.setCanAddPost(true);
            blogModel.setUrlAddPost("/post/add");
        } else {
            blogModel.setCanAddPost(false);
        }
        return new ModelAndView("blog", "blogModel", blogModel);
    }

    @RequestMapping("/blog/{username}/posts")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsForUser(@PathVariable("username") String username) {
        User user = userService.get(username);
        Wall wall = userService.getWall(user);
        return postService.listForWall(wall);
    }

}
