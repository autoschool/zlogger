package zlogger.logic.services;

import zlogger.logic.models.Comment;

import java.util.List;

public interface CommentService {
    public Long addCommentForPost(Long postId, Comment comment);

    public List<Comment> listCommentsForPost(Long postId);

    public void deleteComment(Long id);

    public Comment getComment(Long id);

    public void updateComment(Comment comment);
}
