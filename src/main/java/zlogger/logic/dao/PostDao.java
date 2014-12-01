package zlogger.logic.dao;

import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.List;

public interface PostDao extends GenericDao<Long, Post> {

    public List<Post> listForWall(Wall wall);

    public List<Post> listForUser(User user);

}
