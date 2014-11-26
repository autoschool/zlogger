package zlogger.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;

import java.util.List;

@Repository
public class PostDaoHibernateImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Post> getPosts() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Post.class).list();
    }

    @Override
    public Post getPostById(Long id) {
        return (Post) sessionFactory.openSession()
                .load(Post.class, id);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = (Post) sessionFactory.getCurrentSession()
                .load(Post.class, id);
        sessionFactory.getCurrentSession().delete(post);
    }

    @Override
    public Long createPost(Post post) {
        sessionFactory.getCurrentSession().save(post);
        return post.getId();
    }

    @Override
    public Long updatePost(Post post) {
        sessionFactory.getCurrentSession().merge(post);
        return post.getId();
    }
}
