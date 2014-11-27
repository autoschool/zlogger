package zlogger.logic.services;

import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;

import java.util.List;

public interface CommentaryService {

    public Long addCommentary(Commentary commentary, Post post, User user);

    public List<Commentary> listCommentaries();

    public List<Commentary> listCommentariesForPost(Post post);

    public List<Commentary> listCommentariesForUser(User user);

    public void deleteCommentary(Long id);

    public Commentary getCommentary(Long id);

    public Long updateCommentary(Commentary commentary);

}
