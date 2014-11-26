package zlogger.logic.dao;

import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;

import java.util.List;

public interface CommentaryDao {

    public List<Commentary> getCommentaries();

    public List<Commentary> getCommentariesByPost(Post post);

    public List<Commentary> getCommentariesByUser(User user);

    public Commentary getCommentaryById(Long id);

    public void deleteCommentaryById(Long id);

    public Long createCommentary(Commentary commentary);

    public Long updateCommentary(Commentary commentary);

}
