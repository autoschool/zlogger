package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonPropertyOrder({"id", "title", "message", "creationDate", "creator", "wall"})
public class Post extends AbstractPost {

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "wall_id", nullable = false)
    private Wall wall;

    public Post() {
    }

    public Post(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}