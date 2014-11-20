package zlogger.logic.services;

import zlogger.logic.models.Post;

import java.util.List;

public interface PostService {
    public Long addPost(Post post);

    public List<Post> listPosts();

    public void deletePost(Long id);

    public Post getPost(Long id);

    public void updatePost(Post post);
}
