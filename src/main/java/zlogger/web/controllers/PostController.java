package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.PagedList;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.BlogModel;
import zlogger.web.models.PostModel;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CommentaryService commentaryService;
    @Autowired
    UserService userService;

    @GET
    @RequestMapping("/")
    public ModelAndView displayHome(Authentication authentication,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @CookieValue(value = "pageSize", defaultValue = "5") int pageSize) {
        BlogModel model = new BlogModel();
        PagedList<Post> posts;
        if (authentication == null) {
            model.setBlogName("Zlogger");
            model.setCanAddPost(false);
            posts = postService.list(page, pageSize);
        } else {
            String userName = authentication.getName();
            model.setBlogName(userName + "\'s blog - Zlogger");
            model.setCanAddPost(true);
            posts = postService.list(userName, page, pageSize);
        }
        model.setPosts(posts);
        return new ModelAndView("blog", "blogModel", model);
    }

    @GET
    @RequestMapping("/common")
    public ModelAndView displayAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @CookieValue(value = "pageSize", defaultValue = "5") int pageSize) {
        BlogModel blogModel = new BlogModel();
        PagedList<Post> posts = postService.list(page, pageSize);
        blogModel.setBlogName("Zlogger");
        blogModel.setCanAddPost(false);
        blogModel.setPosts(posts);
        return new ModelAndView("blog", "blogModel", blogModel);
    }

    @POST
    @RequestMapping("/addpost")
    public String addPost(@RequestParam String title, @RequestParam String message,
                          Authentication authentication) {
        User user = userService.get(authentication.getName());
        Wall wall = userService.getWall(user);
        Post post = new Post(title, message);
        postService.add(post, wall, user);

        return "redirect:/";
    }

    @GET
    @RequestMapping("/post/{id}")
    public ModelAndView showPost(@PathVariable("id") long postId, Authentication authentication) {
        PostModel model = new PostModel();
        model.setPost(postService.get(postId));
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
