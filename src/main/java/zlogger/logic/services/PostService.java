package zlogger.logic.services;

import zlogger.logic.models.Post;
import java.util.List;

public interface PostService {
    public void addPost(Post contact);
    public List<Post> listPosts();
    public void removePost(Integer id);
}
