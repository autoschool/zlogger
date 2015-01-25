package zlogger.web.models;


import zlogger.logic.models.PagedList;
import zlogger.logic.models.Post;
import zlogger.logic.models.UserDetails;

import java.util.List;

public class BlogModel {

    private PagedList<Post> posts;
    private String blogName;
    private boolean canAddPost;
    private UserDetails owner;

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public List<Post> getPosts() {
        return posts.getItems();
    }

    public void setPosts(PagedList<Post> posts) {
        this.posts = posts;
    }

    public boolean getHasNextPage() {
        return posts.hasNextPage();
    }

    public boolean getHasPreviousPage() {
        return posts.hasPreviousPage();
    }

    public String getLinkNextPage() {
        return "/?page=" + Integer.toString(posts.getPageNumber() + 1);
    }

    public String getLinkPreviousPage() {
        return "/?page=" + Integer.toString(posts.getPageNumber() - 1);
    }

    public boolean isCanAddPost() {
        return canAddPost;
    }

    public void setCanAddPost(boolean canAddPost) {
        this.canAddPost = canAddPost;
    }

    public UserDetails getOwner() {
        return owner;
    }

    public void setOwner(UserDetails owner) {
        this.owner = owner;
    }


}
