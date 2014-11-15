package zlobber.logic.services;

import zlobber.logic.models.Post;
import java.util.List;

public interface IPostService {
    public void addPost(Post contact);
    public List<Post> listPosts();
    public void removePost(Integer id);
}
