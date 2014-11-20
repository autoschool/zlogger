package zlogger.logic.dal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zlogger.logic.models.Post;

import java.util.List;

@Repository
public class PostDaoHibernateImpl implements PostDao {

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
    public List<Post> getPosts() {
        return getCurrentSession().createCriteria(Post.class).list();
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
        if (post.getId() != null) {
            Post oldPost = getPostById(post.getId());
            if (oldPost == null)
                return null;

            if (post.getMessage() == null) {
                post.setMessage(oldPost.getMessage());
            }
            if (post.getTitle() == null) {
                post.setTitle(oldPost.getTitle());
            }
            post.setCreationDate(oldPost.getCreationDate());
            post.setWall(oldPost.getWall());
            post.setCreator(oldPost.getCreator());
        }
        getCurrentSession().merge(post);
        return post.getId();
    }
}
