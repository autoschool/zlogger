package zlogger.logic.dal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.models.User;

import java.util.List;

/**
 * Created by alexwyrm on 11/20/14.
 */
@Repository
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
    }

    @Override
    public User getUserByName(String name) {
        return (User) sessionFactory.openSession()
                .load(User.class, name);
    }

    @Override
    public void deleteUserByName(String name) {
        User user = (User) sessionFactory.getCurrentSession()
                .load(User.class, name);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public String createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user.getUsername();
    }

    @Override
    public String updateUser(User user) {
        if (user.getUsername() != null) {
            User oldUser = getUserByName(user.getUsername());
            if (oldUser == null) {
                return null;
            }
            if (user.getPassword() == null) {
                user.setPassword(oldUser.getPassword());
            }
            user.setUsername(oldUser.getUsername());
        }
        sessionFactory.getCurrentSession().merge(user);
        return user.getUsername();
    }
}
