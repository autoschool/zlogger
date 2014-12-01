package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zlogger.logic.models.User;
import zlogger.logic.services.UserService;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getUsers() {
        return userService.list();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable("name") String name) {
        return userService.get(name);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("name") String name) {
        User user = userService.get(name);
        userService.delete(user);
    }

}
