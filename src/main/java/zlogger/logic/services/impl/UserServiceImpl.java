package zlogger.logic.services.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.UserDao;
import zlogger.logic.models.PagedList;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.UserService;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getGlobal();
    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public PagedList<User> list(int pageNumber, int pageSize) {
        return new PagedList(userDao.list(pageNumber, pageSize), userDao.countAll(), pageNumber, pageSize);
    }

    @Override
    public Long countAll() {
        return userDao.countAll();
    }

    @Override
    @Transactional
    public String add(User user) {
        Objects.requireNonNull(user, "Can't add null user");
        try {
            return userDao.create(user);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.WARNING, e.toString());
            throw new IllegalArgumentException("User is malformed. " +
                    "Violated constraint: " + e.getConstraintName()
            );
        }
    }

    @Override
    @Transactional
    public User get(String name) {
        Objects.requireNonNull(name, "Can't get user with null username");
        return userDao.get(name);
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
        return userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        Objects.requireNonNull(user, "Can't delete null user");
        userDao.delete(user);
    }

    @Override
    public Wall getWall(User owner) {
        Objects.requireNonNull(owner, "Can't get wall of null user");
        Objects.requireNonNull(owner.getUsername(), "Can't get wall of user with null username");
        return userDao.getWall(owner);
    }
}
