package zlogger.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

public class TestUtilities {

    @Autowired
    SessionFactory sessionFactory;

    private Post constantPost;
    private User constantUser;
    private Wall constantWall;

    private static final String USERNAME = "testAdmin";
    private static final Long WALL_ID = 1L;
    private static final Long POST_ID = 1L;

    public TestUtilities() {
        constantPost = new Post();
        constantPost.setId(POST_ID);
        constantUser = new User(USERNAME);
        constantUser.setEnabled(true);
        constantWall = new Wall();
        constantWall.setWallId(WALL_ID);
    }

    public Post getConstantPost() {
        return constantPost;
    }

    public User getConstantUser() {
        return constantUser;
    }

    public Wall getConstantWall() {
        return constantWall;
    }

    public boolean isPersistent(Object object) {
        return sessionFactory.openSession().contains(object);
    }

}
