package zlogger.logic.dao;

import zlogger.logic.models.Post;
import zlogger.logic.models.Wall;

import java.util.List;

public interface PostDao extends GenericDao<Long, Post> {

    public List<Post> listForWall(Wall wall);

    public List<Post> listForWall(Wall wall, int pageNumber, int pageSize);

    public long countForWall(Wall wall);

}
