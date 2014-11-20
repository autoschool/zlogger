package zlogger.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dal.UserDao;
import zlogger.logic.models.User;

import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public Long addUser(User user) {
        Long id = userDao.createUser(user);
        return id;
    }

    @Override
    public List<User> listUsers() {
        List<User> list = userDao.listUsers();
        return list;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUser(Long id) {
        User user = userDao.getUserById(id);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
