package zlogger.logic.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.List;

@Repository
public class PostDaoHibernateImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Post> list() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Post.class);
        return criteria.list();
    }

    @Override
    public List<Post> list(int pageNumber, int pageSize) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Post.class);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    @Override
    public Long countAll() {
        return (Long)sessionFactory.openSession()
                .createCriteria(Post.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public List<Post> listForWall(Wall wall) {
        return sessionFactory.openSession()
                .createCriteria(Post.class)
                .add(Restrictions.eq("wall", wall))
                .list();
    }

    @Override
    public List<Post> listForUser(User user) {
        return sessionFactory.openSession()
                .createCriteria(Post.class)
                .add(Restrictions.eq("creator", user))
                .list();
    }

    @Override
    public Post get(Long id) {
        return (Post) sessionFactory.openSession()
                .load(Post.class, id);
    }

    @Override
    public void delete(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }

    @Override
    public Long create(Post post) {
        sessionFactory.getCurrentSession().save(post);
        return post.getId();
    }

    @Override
    public Long update(Post post) {
        sessionFactory.getCurrentSession().merge(post);
        return post.getId();
    }
}
