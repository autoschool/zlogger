package zlogger.logic.services;

import zlogger.logic.models.User;

import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
public interface UserService {

    public Long addUser(User user);

    public List<User> listUsers();

    public void deleteUser(Long id);

    public User getUser(Long id);

    public void updateUser(User post);

}
