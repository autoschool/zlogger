package zlogger.logic.dal;

import zlogger.logic.models.User;

import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
public interface UserDao {

    public List<User> listUsers();

    public User getUserById(Long id);

    public void deleteUserById(Long id);

    public Long createUser(User user);

    public Long updateUser(User user);

}
