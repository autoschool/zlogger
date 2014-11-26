package zlogger.logic.services;

import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.List;

public interface PostService {
    public Long addPost(Post post, Wall wall, User user);

    public List<Post> listPosts();

    public List<Post> listPostsForWall(Wall wall);

    public List<Post> listPostsForUser(User user);

    public void deletePost(Long id);

    public Post getPost(Long id);

    public Long updatePost(Post post);
}
