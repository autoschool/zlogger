package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by alexwyrm on 11/20/14.
 */
@Entity
@Table(name = "Walls")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wall {

    @Id
    @GeneratedValue
    @Column(name = "wall_id")
    private Long wallId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

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
}
