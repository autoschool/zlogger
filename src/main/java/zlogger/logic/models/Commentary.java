package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alexwyrm on 11/24/14.
 */
@Entity
@Table(name = "commentaries", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commentary extends AbstractPost implements Serializable {

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
