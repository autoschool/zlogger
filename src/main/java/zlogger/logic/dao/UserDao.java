package zlogger.logic.dao;

import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

public interface UserDao extends GenericDao<String, User> {

    public Wall getWall(User owner);

}
