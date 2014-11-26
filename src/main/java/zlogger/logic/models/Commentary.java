package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity
@Table(name = "commentaries", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonPropertyOrder({"id", "message", "creationDate", "creator", "post"})
public class Commentary extends AbstractPost {

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Commentary() {
    }

    public Commentary(String message) {
        this.message = message;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
