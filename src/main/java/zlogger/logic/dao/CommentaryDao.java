package zlogger.logic.dao;

import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;

import java.util.List;

public interface CommentaryDao extends GenericDao<Long, Commentary> {

    public List<Commentary> listByPost(Post post);

    public List<Commentary> listForUser(User user);

}
