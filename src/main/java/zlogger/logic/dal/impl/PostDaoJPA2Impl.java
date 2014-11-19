package zlogger.logic.dal.impl;

import zlogger.logic.dal.PostDao;
import zlogger.logic.models.Post;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by alexwyrm on 11/19/14.
 */
public class PostDaoJPA2Impl implements PostDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> getPosts() {
        String qlString = "SELECT p FROM Post p";
        TypedQuery<Post> query = em.createQuery(qlString, Post.class);

        return query.getResultList();
    }

    @Override
    public Post getPostById(Long id) {
        try {
            String qlString = "SELECT p FROM Post p WHERE p.id=?1";
            TypedQuery<Post> query = em.createQuery(qlString, Post.class);
            query.setParameter(1, id);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long deletePostById(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);

        return 1L;
    }

    @Override
    public Long createPost(Post post) {
        post.setCreationDate(new Date());
        em.persist(post);
        em.flush();

        return post.getId();
    }

    @Override
    public int updatePost(Post post) {
        em.merge(post);

        return 1;
    }

    @Override
    public void deletePosts() {
        Query query = em.createNativeQuery("TRUNCATE TABLE posts");
        query.executeUpdate();
    }
}
