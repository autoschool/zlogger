package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities", uniqueConstraints =
@UniqueConstraint(columnNames = {"username", "authority"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Authority implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @Column(name = "authority", nullable = false, length = 50)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
}
