package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.IndexModel;

@Controller
public class UserController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @RequestMapping("/{username}")
    public ModelAndView getWall(@PathVariable("username") String username, Authentication authentication) {
        IndexModel indexModel = new IndexModel();
        indexModel.setWelcome(username + "'s blog");
        Wall wall = userService.getWall(new User(username, null));
        indexModel.setPosts(postService.listForWall(wall));
        if (authentication != null && wall.getOwner().getUsername().equals(authentication.getName())) {
            indexModel.setCanAddPost(true);
        } else {
            indexModel.setCanAddPost(false);
        }
        return new ModelAndView("list", "indexModel", indexModel);
    }

}
