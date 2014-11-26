package zlogger.logic.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alexwyrm on 11/26/14.
 */
@MappedSuperclass
public abstract class AbstractPost {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "message", nullable = false, length = 2000)
    protected String message;

    @Column(name = "creation_date", nullable = false)
    protected Date creationDate;

    @ManyToOne
    @JoinColumn(name = "creator_name", nullable = false)
    protected User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
