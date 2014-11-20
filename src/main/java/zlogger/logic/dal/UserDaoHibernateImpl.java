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
    public User getUserById(Long id) {
        return (User) sessionFactory.openSession()
                .load(User.class, id);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = (User) sessionFactory.getCurrentSession()
                .load(User.class, id);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public Long createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user.getUserId();
    }

    @Override
    public Long updateUser(User user) {
        if (user.getUserId() != null) {
            User oldUser = getUserById(user.getUserId());
            if (oldUser == null) {
                return null;
            }

            user.setLogin(oldUser.getLogin());
        }
        sessionFactory.getCurrentSession().merge(user);
        return user.getUserId();
    }
}
