package zlobber.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zlobber.logic.models.Post;
import zlobber.logic.services.IPostService;

import java.util.List;

@Controller
public class PostContoller {


    //@Autowired
    IPostService postService;

    @RequestMapping("/list")
    public
    @ResponseBody
    List<Post> userPosts(@RequestParam(value = "name", required = false, defaultValue = "username") String name) {
        List<Post> posts = postService.listPosts();
        return posts;
    }
}
