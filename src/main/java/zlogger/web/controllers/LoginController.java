package zlogger.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("login")
    public ModelAndView getLoginForm(
            @RequestParam(required = false) String authfailed, String logout,
            String denied) {
        String message = "";
        if (authfailed != null) {
            message = "Invalid username of password, try again !";
        } else if (logout != null) {
            message = "Logged Out successfully, login again to continue !";
        } else if (denied != null) {
            message = "Access denied for this user !";
        }
        return new ModelAndView("login", "message", message);
    }

    @RequestMapping("user")
    public String geUserPage() {
        return "user";
    }

    @RequestMapping("admin")
    public String geAdminPage() {
        return "admin";
    }

    @RequestMapping("403page")
    public String ge403denied() {
        return "redirect:login?denied";
    }

}
