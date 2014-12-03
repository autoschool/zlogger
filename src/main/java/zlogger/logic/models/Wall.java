package zlogger.logic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
