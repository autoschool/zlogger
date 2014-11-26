package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "walls", uniqueConstraints = {@UniqueConstraint(columnNames = "wall_id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wall {

    @Id
    @GeneratedValue
    @Column(name = "wall_id", nullable = false)
    private Long wallId;

    @ManyToOne
    @JoinColumn(name = "owner_name", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    public Long getWallId() {
        return wallId;
    }

    public void setWallId(Long wallId) {
        this.wallId = wallId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
