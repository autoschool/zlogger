package zlogger.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public String getLoginForm(
            @RequestParam(required = false) String authfailed,
            String denied, HttpServletResponse response) throws IOException {
        if (authfailed != null || denied != null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return "login";
        }
        return "login";
    }

}
