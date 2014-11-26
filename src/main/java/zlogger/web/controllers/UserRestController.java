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
        return postService.listUsers();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    String addUser(@RequestBody User user) {
        return postService.addUser(user);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    User getUser(@PathVariable("name") String name) {
        return postService.getUser(name);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        postService.updateUser(user);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("name") String name) {
        postService.deleteUser(name);
    }

}
