package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.models.PagedList;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.AuthenticationService;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.models.BlogModel;
import zlogger.web.models.PostModel;

import javax.ws.rs.core.MediaType;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CommentaryService commentaryService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
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
            model.setOwner(userService.getUserDetails(userName));
        }
        model.setPosts(posts);
        return new ModelAndView("blog", "blogModel", model);
    }

    @RequestMapping(value = "/common",
            method = RequestMethod.GET)
    public ModelAndView displayAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @CookieValue(value = "pageSize", defaultValue = "5") int pageSize) {
        BlogModel blogModel = new BlogModel();
        PagedList<Post> posts = postService.list(page, pageSize);
        blogModel.setBlogName("Zlogger");
        blogModel.setCanAddPost(false);
        blogModel.setPosts(posts);
        return new ModelAndView("blog", "blogModel", blogModel);
    }

    @RequestMapping(value = "/user/addpost",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> addPost(@RequestBody Post post,
                                          Authentication authentication) {
        User user = userService.get(authentication.getName());
        Wall wall = userService.getWall(user);
        postService.add(post, wall, user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/editpost",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public void editPost(@RequestBody Post post) {
        postService.update(post);
    }

    @RequestMapping(value = "/user/deletepost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@PathVariable("id") long postId,
                                             Authentication authentication) {
        Post post = postService.get(postId);

        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!authenticationService.isCreator(authentication, post)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        postService.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{id}",
            method = RequestMethod.GET)
    public ModelAndView showPost(@PathVariable("id") long postId,
                                 Authentication authentication) {
        PostModel model = new PostModel();
        model.setPost(postService.get(postId));
        if (authentication == null) {
            model.setCanAddCommentary(false);
        } else {
            model.setCanAddCommentary(true);
        }
        return new ModelAndView("post", "postModel", model);
    }

}
