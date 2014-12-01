package zlogger.logic.services.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.UserDao;
import zlogger.logic.models.User;
import zlogger.logic.services.UserService;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public List<User> list() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public String add(User user) {
        Objects.requireNonNull(user, "Can't add null user");
        try {
            return userDao.createUser(user);
        } catch (ConstraintViolationException e) {
            Logger logger = Logger.getGlobal();
            logger.log(Level.WARNING, "ConstraintViolationException: " + e);
            throw new IllegalArgumentException("User is malformed. " +
                    "Violated constraint: " + e.getConstraintName()
            );
        }
    }

    @Override
    @Transactional
    public User get(String name) {
        Objects.requireNonNull(name, "Can't get user with null username");
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public String update(User user) {
        Objects.requireNonNull(user, "Can't update with null user");
        Objects.requireNonNull(user.getUsername(),
                "Can't update user with null username");

        User oldUser = get(user.getUsername());

        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        if (user.getEnabled() == null) {
            user.setEnabled(oldUser.getEnabled());
        }
        user.setUsername(oldUser.getUsername());
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void delete(String name) {
        Objects.requireNonNull(name, "Can't delete user with null username");
        userDao.deleteUserByName(name);
    }
}
