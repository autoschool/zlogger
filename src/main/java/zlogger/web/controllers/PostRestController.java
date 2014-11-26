package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Post> getPosts() {
        return postService.listPosts();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long addPost(@RequestBody Post post) {
        User creator = userService.getUser(post.getCreator().getUsername());
        return postService.addPost(post, post.getWall(), creator);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public
    Post getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody Post post) {
        postService.updatePost(post);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }


}
