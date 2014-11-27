package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    @JsonIgnore
    private String password;

    @Column(name = "enabled", nullable = false)
    @JsonIgnore
    private Boolean enabled;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "wallId")
    @JsonIgnore
    private Set<Wall> walls = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private Set<Commentary> commentaries = new HashSet<>();

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public void setWalls(Set<Wall> walls) {
        this.walls = walls;
    }

    public Set<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(Set<Commentary> commentaries) {
        this.commentaries = commentaries;
    }
}
