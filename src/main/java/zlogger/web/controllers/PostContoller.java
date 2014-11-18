package zlogger.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zlogger.logic.models.Post;
import zlogger.logic.services.PostService;

import java.util.List;

@Controller
public class PostContoller {


    //@Autowired
    PostService postService;

    @RequestMapping("/list")
    public
    @ResponseBody
    List<Post> userPosts(@RequestParam(value = "name", required = false, defaultValue = "username") String name) {
        List<Post> posts = postService.listPosts();
        return posts;
    }
}
