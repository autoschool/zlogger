package zlogger.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dal.PostDao;
import zlogger.logic.models.Post;

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
        postDao.updatePost(post);
    }
}
