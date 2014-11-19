package zlogger.logic.dal;

import zlogger.logic.models.Post;

import java.util.List;

public interface PostDao {

    public List<Post> getPosts();

    public Post getPostById(Long id);

    public void deletePostById(Long id);

    public Long createPost(Post post);

    public Long updatePost(Post post);

}
