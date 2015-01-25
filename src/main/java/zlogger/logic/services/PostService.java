package zlogger.logic.services;

import zlogger.logic.models.PagedList;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.List;

public interface PostService extends GenericService<Post, Long> {

    public Long add(Post post, Wall wall, User user);

    public List<Post> list(User user);

    public List<Post> list(String username);

    public PagedList<Post> list(String username, int page, int pageSize);


}
