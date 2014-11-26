package zlogger.logic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.UserDao;
import zlogger.logic.models.User;
import zlogger.logic.services.UserService;

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
        if (user.getUsername() != null) {
            User oldUser = getUser(user.getUsername());


            if (user.getPassword() == null) {
                user.setPassword(oldUser.getPassword());
            }
            if (user.getEnabled() == null) {
                user.setEnabled(oldUser.getEnabled());
            }
            user.setUsername(oldUser.getUsername());
        }
        userDao.updateUser(user);
    }
}
