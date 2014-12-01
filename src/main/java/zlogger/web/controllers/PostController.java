package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.IndexModel;
import zlogger.web.models.PostModel;

@Controller
//@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentaryService commentaryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public ModelAndView getList(Authentication authentication) {
        IndexModel model = new IndexModel();
        if (authentication == null) {
            model.setWelcome("Hello anonymous");
            model.setPosts(postService.list());
            model.setCanAddPost(false);
        } else {
            String userName = authentication.getName();
            User user = new User(userName, null);
            model.setWelcome("Hello " + userName);
            Wall wall = userService.getWall(user);
            model.setPosts(postService.listForWall(wall));
            model.setCanAddPost(true);
        }
        model.setUrlAddPost("/post/add");
        return new ModelAndView("list", "indexModel", model);
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addPost(@ModelAttribute Post post, Authentication authentication) {
        User user = new User(authentication.getName(), null);
        Wall wall = userService.getWall(user);
        postService.add(post, wall, user);

        return "forward:/list";
    }

    @RequestMapping("/post/{id}")
    public ModelAndView showPost(@PathVariable("id") long postId, Authentication authentication) {
        PostModel model = new PostModel();
        model.setPost(postService.get(postId));
        //model.setCommentaries(commentaryService.listCommentariesForPost(model.getPost())); load by ajax
        model.setUrlLoadCommentary("/post/" + postId + "/commentaries");
        if (authentication == null) {
            model.setCanAddCommentary(false);
        } else {
            model.setCanAddCommentary(true);
        }
        //todo implement
        model.setUrlAddCommentary("/post/" + postId + "/addcomment");
        return new ModelAndView("post", "postModel", model);
    }

}
