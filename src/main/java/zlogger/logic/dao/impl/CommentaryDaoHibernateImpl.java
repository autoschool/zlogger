package zlogger.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dao.CommentaryDao;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentaryDaoHibernateImpl implements CommentaryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Commentary> getCommentaries() {
        return sessionFactory.openSession()
                .createCriteria(Commentary.class).list();
    }

    @Override
    public List<Commentary> getCommentariesByPost(Post post) {
        return new ArrayList<>(post.getCommentaries());
    }

    @Override
    public List<Commentary> getCommentariesByUser(User user) {
        return new ArrayList<>(user.getCommentaries());
    }

    @Override
    public Commentary getCommentaryById(Long id) {
        return (Commentary) sessionFactory.openSession()
                .load(Commentary.class, id);
    }

    @Override
    public void deleteCommentaryById(Long id) {
        Commentary Commentary = (Commentary) sessionFactory.getCurrentSession()
                .load(Commentary.class, id);
        sessionFactory.getCurrentSession().delete(Commentary);
    }

    @Override
    public Long createCommentary(Commentary commentary) {
        sessionFactory.getCurrentSession().save(commentary);
        return commentary.getId();
    }

    @Override
    public Long updateCommentary(Commentary commentary) {
        sessionFactory.getCurrentSession().merge(commentary);
        return commentary.getId();
    }

}
