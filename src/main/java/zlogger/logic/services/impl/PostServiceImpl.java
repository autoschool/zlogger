package zlogger.logic.services.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.PostService;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    @Transactional
    public Long addPost(Post post, Wall wall, User user) {
        Objects.requireNonNull(wall, "Can't create post with null wall");
        Objects.requireNonNull(user, "Can't create post with null user");
        Objects.requireNonNull(wall.getWallId(),
                "Can't create post with null wall_id");
        Objects.requireNonNull(user.getUsername(),
                "Can't create post with null creator_id");
        if (!user.getEnabled()) {
            throw new IllegalStateException("Can't add post with disabled creator");
        }

        post.setWall(wall);
        post.setCreator(user);
        post.setCreationDate(new Date());
        try {
            return postDao.createPost(post);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Post is malformed or it's " +
                    "dependencies are not persistent. " +
                    "Violated constraint: " + e.getConstraintName()
            );
        }
    }


    @Override
    @Transactional
    public List<Post> listPosts() {
        return postDao.getPosts();
    }

    @Override
    @Transactional
    public List<Post> listPostsForWall(Wall wall) {
        Objects.requireNonNull(wall, "Can't get posts for null wall");
        Objects.requireNonNull(wall.getWallId(),
                "Can't get posts for wall with null wall_id");

        return postDao.getPostsByWall(wall);
    }

    @Override
    @Transactional
    public List<Post> listPostsForUser(User user) {
        Objects.requireNonNull(user, "Can't get posts for null user");
        Objects.requireNonNull(user.getUsername(),
                "Can't get posts for user with null username");

        return postDao.getPostsByUser(user);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Objects.requireNonNull(id, "Can't delete post with null id");

        postDao.deletePostById(id);
    }

    @Override
    @Transactional
    public Post getPost(Long id) {
        Objects.requireNonNull(id, "Can't get post with null id");

        return postDao.getPostById(id);
    }

    @Override
    @Transactional
    public Long updatePost(Post post) {
        Objects.requireNonNull(post, "Can't update with null post");
        Objects.requireNonNull(post.getId(), "Can't update post with null id");

        Post oldPost = getPost(post.getId());

        if (post.getMessage() == null) {
            post.setMessage(oldPost.getMessage());
        }
        if (post.getTitle() == null) {
            post.setTitle(oldPost.getTitle());
        }
        post.setCreationDate(oldPost.getCreationDate());
        post.setWall(oldPost.getWall());
        post.setCreator(oldPost.getCreator());
        return postDao.updatePost(post);
    }
}
