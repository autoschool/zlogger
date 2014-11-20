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
        Long id = postDao.createPost(post);
        return id;
    }


    @Override
    public List<Post> listPosts() {
        List<Post> list = postDao.getPosts();
        return list;
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        postDao.deletePostById(id);
    }

    @Override
    public Post getPost(Long id) {
        Post post = postDao.getPostById(id);
        return post;
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        postDao.updatePost(post);
    }
}
