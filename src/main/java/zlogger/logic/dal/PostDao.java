package zlogger.logic.dal;

import zlogger.logic.models.Post;

import java.util.List;

/**
 * Created by alexwyrm on 11/19/14.
 */
public interface PostDao {

    public List<Post> getPosts();

    public Post getPostById(Long id);

    public Long deletePostById(Long id);

    public Long createPost(Post post);

    public int updatePost(Post post);

    public void deletePosts();

}
