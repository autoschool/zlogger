package zlogger.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

public class TestUtilities {

    @Autowired
    SessionFactory sessionFactory;

    private Post constantPost;
    private User constantUser;
    private Wall constantWall;
    private Commentary constantCommentary;

    private static final String USERNAME = "testAdmin";
    private static final String PASSWORD = "password";
    private static final Long WALL_ID = 1L;
    private static final Long POST_ID = 1L;
    private static final Long COMMENTARY_ID = 1L;

    public TestUtilities() {
        constantPost = new Post();
        constantPost.setId(POST_ID);
        constantUser = new User(USERNAME, PASSWORD);
        constantWall = new Wall();
        constantWall.setWallId(WALL_ID);
        constantCommentary = new Commentary();
        constantCommentary.setId(COMMENTARY_ID);
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

    public Commentary getConstantCommentary() {
        return constantCommentary;
    }

    public boolean isPersistent(Object object) {
        return sessionFactory.openSession().contains(object);
    }

}
