package zlogger.logic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;
import zlogger.logic.services.PostService;

import java.util.Date;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;


    @Override
    @Transactional
    public Long addPost(Post post) {
        post.setCreationDate(new Date());
        return postDao.createPost(post);
    }


    @Override
    @Transactional
    public List<Post> listPosts() {
        return postDao.getPosts();
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        postDao.deletePostById(id);
    }

    @Override
    @Transactional
    public Post getPost(Long id) {
        return postDao.getPostById(id);
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        if (post.getId() != null) {
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
        }
        postDao.updatePost(post);
    }
}
