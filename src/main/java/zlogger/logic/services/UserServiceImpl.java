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
    public String addUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public void deleteUser(String name) {
        userDao.deleteUserByName(name);
    }

    @Override
    @Transactional
    public User getUser(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
