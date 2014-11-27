package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.services.PostService;
import zlogger.web.models.IndexModel;

@Controller
//@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public ModelAndView getList() {
        IndexModel model = new IndexModel();
        model.setWelcome("test welcome");
        model.setPosts(postService.listPosts());
        return new ModelAndView("list", "indexModel", model);
    }


}
