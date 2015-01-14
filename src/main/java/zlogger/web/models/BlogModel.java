package zlogger.web.models;


import zlogger.logic.models.PagedList;
import zlogger.logic.models.Post;

import java.util.List;

public class BlogModel {

    private PagedList<Post> posts;
    private String blogName;
    private boolean canAddPost;
    private String urlAddPost;
    private String urlLoadPost;

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogName() {
        return blogName;
    }

    public List<Post> getPosts() {
        return posts.getItems();
    }

    public boolean getHasNextPage(){
        return posts.hasNextPage();
    }
    public boolean getHasPreviousPage(){
        return posts.hasPreviousPage();
    }
    public String getLinkNextPage(){
        return "/?page="+Integer.toString(posts.getPageNumber()+1);
    }
    public String getLinkPreviousPage(){
        return "/?page="+Integer.toString(posts.getPageNumber()-1);
    }


    public void setPosts(PagedList<Post> posts) {
        this.posts = posts;
    }

    public boolean isCanAddPost() {
        return canAddPost;
    }

    public void setCanAddPost(boolean canAddPost) {
        this.canAddPost = canAddPost;
    }

    public String getUrlAddPost() {
        return urlAddPost;
    }

    public void setUrlAddPost(String urlAddPost) {
        this.urlAddPost = urlAddPost;
    }

    public String getUrlLoadPost() {
        return urlLoadPost;
    }

    public void setUrlLoadPost(String urlLoadPost) {
        this.urlLoadPost = urlLoadPost;
    }
}
