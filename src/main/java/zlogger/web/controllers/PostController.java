package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zlogger.logic.models.Post;
import zlogger.logic.services.PostService;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {


    @Autowired
    PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<Post> userPosts(@RequestParam(value = "name", required = false, defaultValue = "username") String name) {
        List<Post> posts = postService.listPosts();
        return posts;
    }
}
