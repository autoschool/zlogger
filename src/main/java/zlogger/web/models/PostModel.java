package zlogger.web.models;


import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;

import java.util.List;

public class PostModel {
    private List<Commentary> commentaries;
    private Post post;

    private boolean canAddCommnetary;
    private String urlAddCommentary;
    private String urlLoadCommentary;

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public boolean isCanAddCommnetary() {
        return canAddCommnetary;
    }

    public void setCanAddCommnetary(boolean canAddCommnetary) {
        this.canAddCommnetary = canAddCommnetary;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUrlAddCommentary() {
        return urlAddCommentary;
    }

    public void setUrlAddCommentary(String urlAddCommentary) {
        this.urlAddCommentary = urlAddCommentary;
    }

    public String getUrlLoadCommentary() {
        return urlLoadCommentary;
    }

    public void setUrlLoadCommentary(String urlLoadCommentary) {
        this.urlLoadCommentary = urlLoadCommentary;
    }
}
