package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alexwyrm on 11/24/14.
 */
@Entity
@Table(name = "authorities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Authority implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
}
