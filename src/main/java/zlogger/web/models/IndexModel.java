package zlogger.web.models;


import zlogger.logic.models.Post;

import java.util.List;

public class IndexModel {
    private List<Post> posts;
    private String welcome;

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getWelcome() {
        return welcome;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
