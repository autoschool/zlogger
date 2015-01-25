package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Controller
public class CommentariesController {
    @Autowired
    PostService postService;
    @Autowired
    CommentaryService commentaryService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/post/{id}/commentaries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Commentary> showPost(@PathVariable("id") long postId) {
        return commentaryService.listForPost(postService.get(postId));
    }

    @RequestMapping(value = "/post/{id}/addcomment",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable("id") long postId,
                           Authentication authentication,
                           @RequestBody Commentary commentary,
                           HttpServletResponse response) throws IOException {
        if (authentication != null) {
            User user = userService.get(authentication.getName());
            Post toPost = postService.get(postId);
            commentaryService.add(commentary, toPost, user);
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
