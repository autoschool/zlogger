package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.services.PostService;

import java.util.List;

@Controller
//@RequestMapping("/posts")
public class PostController {


    @Autowired
    PostService postService;

    @RequestMapping("/")
    public ModelAndView getList() {
        List posts = postService.listPosts();
        return new ModelAndView("list", "postList", posts);
    }

}
