package zlogger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.GenericService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;

import java.util.List;

public abstract class BasicTest {

    private static final String TEST_MESSAGE = "message";
    private static final String TEST_TITLE = "title";
    private static final String TEST_USERNAME = "user";
    private static final String TEST_PASSWORD = "password";
    @Autowired
    protected PostService postService;
    @Autowired
    protected UserService userService;
    @Autowired
    private SessionFactory sessionFactory;

    public Object getValidObject(GenericService service) {
        if (service instanceof PostService) {
            Post post = new Post(TEST_TITLE, TEST_MESSAGE);
            User user = (User) getFirst(userService);
            post.setWall(userService.getWall(user));
            post.setCreator(user);
            return post;
        }
        if (service instanceof CommentaryService) {
            Commentary commentary = new Commentary(TEST_MESSAGE);
            commentary.setPost((Post) getFirst(postService));
            commentary.setCreator((User) getFirst(userService));
            return commentary;
        }
        if (service instanceof UserService) {
            return new User(TEST_USERNAME, TEST_PASSWORD);
        }
        return null;
    }

    public Object getInvalidObject(GenericService service) {
        if (service instanceof PostService) {
            Post post = new Post(TEST_TITLE, null);
            User user = (User) getFirst(userService);
            post.setWall(userService.getWall(user));
            post.setCreator(user);
            return post;
        }
        if (service instanceof CommentaryService) {
            Commentary commentary = new Commentary(null);
            commentary.setPost((Post) getFirst(postService));
            commentary.setCreator((User) getFirst(userService));
            return commentary;
        }
        if (service instanceof UserService) {
            return new User(TEST_USERNAME, null);
        }
        return null;
    }

    public void updateObject(GenericService service, Object object) {
        if (service instanceof PostService) {
            Post post = (Post) object;
            post.setTitle(post.getTitle() + "a");
        }
        if (service instanceof CommentaryService) {
            Commentary commentary = (Commentary) object;
            commentary.setMessage(commentary.getMessage() + "a");
        }
        if (service instanceof UserService) {
            User user = (User) object;
            user.setPassword(user.getPassword() + "a");
        }
    }

    public Object getFirst(GenericService service) {
        List list = service.list();
        return list.get(0);
    }

    public User getUser() {
        return userService.list().get(0);
    }

    public Wall getWall() {
        return userService.getWall(userService.list().get(0));
    }

    public Post getPost() {
        return postService.list().get(0);
    }

    public boolean isPersistent(Object object) {
        return sessionFactory.openSession().contains(object);
    }

}
