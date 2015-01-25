package zlogger.web.models;

import zlogger.logic.models.UserDetails;

public class UserModel {

    private String username;
    private UserDetails details;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }
}
