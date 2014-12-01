package zlogger.util;

import org.springframework.beans.factory.annotation.Autowired;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;

public class CRUDTestUtilities {

    private static final String TEST_TITLE = "test";
    private static final String TEST_MESSAGE = "test";
    private static final String TEST_USERNAME = "test";
    private static final String TEST_PASSWORD = "test";

    private int counter = 0;

    @Autowired
    private PostService postService;
    @Autowired
    private CommentaryService commentaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private TestUtilities testUtilities;

    public Post createValidPost() {
        Post post = new Post(TEST_TITLE, TEST_MESSAGE);
        post.setWall(testUtilities.getConstantWall());
        post.setCreator(testUtilities.getConstantUser());
        return post;
    }

    public Commentary createValidCommentary() {
        Commentary commentary = new Commentary(TEST_MESSAGE);
        commentary.setPost(testUtilities.getConstantPost());
        commentary.setCreator(testUtilities.getConstantUser());
        return commentary;
    }

    public User createValidUser() {
        return new User(TEST_USERNAME + counter++, TEST_PASSWORD);
    }

    public Post getRealPost() {
        return postService.list().get(1);
    }

    public Commentary getRealCommentary() {
        return commentaryService.list().get(1);
    }

    public User getRealUser() {
        return userService.list().get(1);
    }

    public PostService getPostService() {
        return postService;
    }

    public CommentaryService getCommentaryService() {
        return commentaryService;
    }

    public UserService getUserService() {
        return userService;
    }
}
