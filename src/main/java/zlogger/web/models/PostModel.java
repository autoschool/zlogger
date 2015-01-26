package zlogger.web.models;


import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;

import java.util.List;

public class PostModel {
    private List<Commentary> commentaries;
    private Post post;

    private boolean canAddCommentary;

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public boolean isCanAddCommentary() {
        return canAddCommentary;
    }

    public void setCanAddCommentary(boolean canAddCommentary) {
        this.canAddCommentary = canAddCommentary;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
