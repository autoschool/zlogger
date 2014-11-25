package zlogger.logic.services;

import zlogger.logic.models.User;

import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
public interface UserService {

    public String addUser(User user);

    public List<User> listUsers();

    public void deleteUser(String name);

    public User getUser(String name);

    public void updateUser(User post);

}
