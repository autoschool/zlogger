package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_details", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDetails implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "email")
    @JsonIgnore
    private String email;

    @Column(name = "about")
    private String about;

    @Column(name = "site")
    private String site;

    public UserDetails() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
