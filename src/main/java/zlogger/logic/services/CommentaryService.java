package zlogger.logic.services;

import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;

import java.util.List;

public interface CommentaryService extends CRUDService<Commentary, Long> {

    public Long add(Commentary commentary, Post post, User user);

    public List<Commentary> listForPost(Post post);

    public List<Commentary> listForUser(User user);

}
