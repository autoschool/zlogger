package zlogger.logic.dao;

import zlogger.logic.models.User;

import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
public interface UserDao {

    public List<User> listUsers();

    public User getUserByName(String name);

    public void deleteUserByName(String name);

    public String createUser(User user);

    public String updateUser(User user);

}
