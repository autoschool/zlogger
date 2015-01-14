package zlogger.logic.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dao.CommentaryDao;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;

import java.util.List;

@Repository
public class CommentaryDaoHibernateImpl implements CommentaryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Commentary> list() {
        return sessionFactory.openSession()
                .createCriteria(Commentary.class).list();
    }

    @Override
    public List<Commentary> list(int pageNumber, int pageSize) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Commentary.class);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    @Override
    public Long countAll() {
        return (Long)sessionFactory.openSession()
                .createCriteria(Commentary.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public List<Commentary> listByPost(Post post) {
        //todo add filter criteria
        return sessionFactory.openSession()
                .createCriteria(Commentary.class)
                .add(Restrictions.eq("post", post))
                .list();
    }

    @Override
    public List<Commentary> listForUser(User user) {
        //todo add filter criteria
        return sessionFactory.openSession()
                .createCriteria(Commentary.class)
                .add(Restrictions.eq("creator", user))
                .list();
    }

    @Override
    public Commentary get(Long id) {
        return (Commentary) sessionFactory.openSession()
                .load(Commentary.class, id);
    }

    @Override
    public void delete(Commentary commentary) {
        sessionFactory.getCurrentSession().delete(commentary);
    }

    @Override
    public Long create(Commentary commentary) {
        sessionFactory.getCurrentSession().save(commentary);
        return commentary.getId();
    }

    @Override
    public Long update(Commentary commentary) {
        sessionFactory.getCurrentSession().merge(commentary);
        return commentary.getId();
    }

}
