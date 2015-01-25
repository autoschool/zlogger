package zlogger.logic.services;

import zlogger.logic.models.User;
import zlogger.logic.models.UserDetails;
import zlogger.logic.models.Wall;

public interface UserService extends GenericService<User, String> {

    public Wall getWall(User owner);

    public Wall getWall(String username);

    public UserDetails getUserDetails(User user);

    public UserDetails getUserDetails(String username);

    public User updateUserDetails(UserDetails userDetails);

    public boolean exists(String name);

}
