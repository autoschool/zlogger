package zlogger.logic.dal.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.dal.PostDao;
import zlogger.logic.models.Post;
import java.util.List;

@Repository
public class PostDaoHibernateImpl implements PostDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Post> getPosts() {
        return getCurrentSession().createQuery("from Posts").list();
    }

    @Override
    public Post getPostById(Long id) {
        Post post = (Post) getCurrentSession().load(Post.class, id);
        return post;
    }

    @Override
    public void deletePostById(Long id) {
        Post post = (Post) getCurrentSession().load(Post.class, id);
        getCurrentSession().delete(post);
    }

    @Override
    public Long createPost(Post post) {
        getCurrentSession().save(post);
        return post.getId();
    }

    @Override
    public Long updatePost(Post post) {
        getCurrentSession().saveOrUpdate(post);
        return post.getId();
    }
}
