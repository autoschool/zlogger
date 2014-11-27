package zlogger.logic.services.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.UserDao;
import zlogger.logic.models.User;
import zlogger.logic.services.UserService;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public String addUser(User user) {
        Objects.requireNonNull(user, "Can't add null user");
        try {
            return userDao.createUser(user);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("User is malformed. " +
                    "Violated constraint: " + e.getConstraintName()
            );
        }
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public void deleteUser(String name) {
        Objects.requireNonNull(name, "Can't delete user with null username");
        userDao.deleteUserByName(name);
    }

    @Override
    @Transactional
    public User getUser(String name) {
        Objects.requireNonNull(name, "Can't get user with null username");
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Objects.requireNonNull(user, "Can't update with null user");
        Objects.requireNonNull(user.getUsername(),
                "Can't update user with null username");

        User oldUser = getUser(user.getUsername());

        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        if (user.getEnabled() == null) {
            user.setEnabled(oldUser.getEnabled());
        }
        user.setUsername(oldUser.getUsername());
        userDao.updateUser(user);
    }
}
