package zlogger.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dao.UserDao;
import zlogger.logic.models.User;

import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> listUsers() {
        return sessionFactory.openSession()
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
        sessionFactory.getCurrentSession().merge(user);
        return user.getUsername();
    }
}
