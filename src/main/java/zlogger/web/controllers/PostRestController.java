package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zlogger.logic.models.Post;
import zlogger.logic.services.PostService;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
@Controller
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    PostService postService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<Post> getPosts() {
        List<Post> posts = postService.listPosts();

        return posts;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Long addPost(@RequestBody Post post) {
        Long id = postService.addPost(post);
        return id;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Post getPost(@PathVariable("id") Long id) {
        Post post = postService.getPost(id);
        return post;
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
