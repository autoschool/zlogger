package zlogger.logic.services;

import org.springframework.security.access.prepost.PreAuthorize;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.List;

public interface PostService extends GenericService<Post, Long> {

    @PreAuthorize("#wall.owner.username == #user.username")
    public Long add(Post post, Wall wall, User user);

    public List<Post> listForWall(Wall wall);

    public List<Post> listForUser(User user);

}
