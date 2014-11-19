package zlogger.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dal.PostDao;
import zlogger.logic.models.Post;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;


    @Override
    @Transactional
    public void addPost(Post post) {
        postDao.createPost(post);
    }



    @Override
    public List<Post> listPosts() {
        List<Post> list = postDao.getPosts();
        return list;
    }

    @Override
    public void removePost(Long id) {
        postDao.deletePostById(id);
    }
}
