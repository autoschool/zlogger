package zlogger.logic.dal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
    private SessionFactory mySessionFactory;

    private Session getCurrentSession() {
        Session session;
        try {
            session = mySessionFactory.getCurrentSession();
        } catch (HibernateException he) {
            session = mySessionFactory.openSession();
        }
        return session;
    }

    @Override
    public List<User> listUsers() {
        return getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public User getUserById(Long id) {
        User user = (User) getCurrentSession().load(User.class, id);
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = (User) getCurrentSession().load(User.class, id);
        getCurrentSession().delete(user);
    }

    @Override
    public Long createUser(User user) {
        getCurrentSession().save(user);
        return user.getUserId();
    }

    @Override
    public Long updateUser(User user) {
        if (user.getUserId() != null) {
            User oldUser = getUserById(user.getUserId());
            if (oldUser == null)
                return null;

            user.setLogin(oldUser.getLogin());
        }
        getCurrentSession().merge(user);
        return user.getUserId();
    }
}
