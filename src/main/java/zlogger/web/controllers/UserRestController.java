package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zlogger.logic.models.User;
import zlogger.logic.services.UserService;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
@Controller
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserService postService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<User> getUsers() {
        List<User> users = postService.listUsers();

        return users;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Long addUser(@RequestBody User user) {
        Long id = postService.addUser(user);
        return id;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    User getUser(@PathVariable("id") Long id) {
        User user = postService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        postService.updateUser(user);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        postService.deleteUser(id);
    }

}
