package zlogger.logic.dao;

import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.List;

public interface PostDao {

    public List<Post> getPosts();

    public List<Post> getPostsByWall(Wall wall);

    public List<Post> getPostsByUser(User user);

    public Post getPostById(Long id);

    public void deletePostById(Long id);

    public Long createPost(Post post);

    public Long updatePost(Post post);

}
