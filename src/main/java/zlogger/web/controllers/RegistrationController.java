package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zlogger.logic.models.User;
import zlogger.logic.models.UserDetails;
import zlogger.logic.services.UserService;
import zlogger.web.models.RegistrationModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Objects;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("signup")
    public String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> registerNewUser(@RequestBody RegistrationModel model) throws IOException {
        if (userService.exists(model.getUserName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User user = new User(model.getUserName(),
                passwordEncoder.encode(model.getPassword()));
        userService.add(user);
        if (Objects.nonNull(model.getEmail())) {
            UserDetails details = userService.getUserDetails(user);
            details.setEmail(model.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
