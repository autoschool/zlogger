package zlogger.logic.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dao.UserDao;
import zlogger.logic.models.Authority;
import zlogger.logic.models.User;
import zlogger.logic.models.UserDetails;
import zlogger.logic.models.Wall;

import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> list() {
        return sessionFactory.openSession()
                .createCriteria(User.class).list();
    }

    @Override
    public List<User> list(int pageNumber, int pageSize) {
        Criteria criteria = sessionFactory.openSession().createCriteria(User.class);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    @Override
    public Long countAll() {
        return (Long) sessionFactory.openSession()
                .createCriteria(User.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public User get(String name) {
        return (User) sessionFactory.openSession()
                .createCriteria(User.class)
                .add(Restrictions.ilike("username", name))
                .uniqueResult();
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public String create(User user) {
        sessionFactory.getCurrentSession().save(user);
        Wall wall = new Wall();
        wall.setOwner(user);
        sessionFactory.getCurrentSession().save(wall);
        UserDetails userDetails = new UserDetails();
        userDetails.setUser(user);
        sessionFactory.getCurrentSession().save(userDetails);
        Authority authority = new Authority();
        authority.setUsername(user);
        authority.setRole("ROLE_USER");
        sessionFactory.getCurrentSession().save(authority);
        return user.getUsername();
    }

    @Override
    public String update(User user) {
        sessionFactory.getCurrentSession().merge(user);
        return user.getUsername();
    }

    @Override
    public Wall getWall(User owner) {
        return (Wall) sessionFactory.openSession()
                .createCriteria(Wall.class)
                .add(Restrictions.eq("owner", owner))
                .uniqueResult();
    }

    @Override
    public UserDetails getUserDetails(User user) {
        return (UserDetails) sessionFactory.openSession()
                .createCriteria(UserDetails.class)
                .add(Restrictions.eq("user", user))
                .uniqueResult();
    }

    @Override
    public User updateUserDetails(UserDetails userDetails) {
        sessionFactory.getCurrentSession().merge(userDetails);
        return userDetails.getUser();
    }
}
