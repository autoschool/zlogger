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
import zlogger.web.models.BlogModel;
import zlogger.web.models.PostModel;

import javax.ws.rs.core.MediaType;

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
    public ModelAndView displayHome(Authentication authentication) {
        BlogModel model = new BlogModel();
        if (authentication == null) {
            model.setBlogName("Common blog");
            model.setUrlLoadPost("/posts");
            model.setCanAddPost(false);
        } else {
            String userName = authentication.getName();
            model.setBlogName(userName + "\'s blog");
            model.setUrlLoadPost("/blog/" + userName + "/posts");
            model.setCanAddPost(true);
        }
        model.setUrlAddPost("/post/add");
        return new ModelAndView("blog", "blogModel", model);
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public String addPost(@RequestBody Post post, Authentication authentication) {
        System.out.println("addPost " + post.getTitle() + " " + post.getMessage());
        User user = userService.get(authentication.getName());
        Wall wall = userService.getWall(user);
        postService.add(post, wall, user);

        return "redirect:/";
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
        model.setUrlAddCommentary("/post/" + postId + "/addcomment");
        return new ModelAndView("post", "postModel", model);
    }

}
