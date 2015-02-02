package zlogger.unit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;
import zlogger.web.controllers.CommentariesController;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommentariesControllerTest {

    public static final long POST_ID = 0L;
    public static final String POST_MESSAGE = "test";
    public static final String POST_TITLE = "test";
    public static final Post TEST_POST = new Post(POST_TITLE, POST_MESSAGE);
    public static final String USER_NAME = "user";
    public static final User TEST_USER = new User(USER_NAME, null);
    public static final Commentary TEST_COMMENTARY = new Commentary(POST_MESSAGE);
    @Mock
    UserService userService;
    @InjectMocks
    private CommentariesController controller;
    @Mock
    private CommentaryService commentaryService;
    @Mock
    private PostService postService;
    @Mock
    private Authentication authentication;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(postService.get(POST_ID)).thenReturn(TEST_POST);
        Mockito.when(commentaryService.listForPost(TEST_POST))
                .thenReturn(Arrays.asList(new Commentary("test1"),
                        new Commentary("test2")));
        Mockito.when(authentication.getName()).thenReturn(USER_NAME);
        Mockito.when(userService.get(USER_NAME)).thenReturn(TEST_USER);
        Mockito.when(commentaryService.add(TEST_COMMENTARY, TEST_POST, TEST_USER))
                .thenReturn(POST_ID);
    }

    @Test
    public void shouldReturnCommentariesForPost() {
        assertThat(controller.showPost(POST_ID).size(), is(2));
    }

    @Test
    public void shouldSuccessfullyAddCommentary() {
        assertThat(controller.addComment(POST_ID, authentication, TEST_COMMENTARY)
                .getStatusCode().value(), is(201));
    }

    @Test
    public void shouldReturnUnauthorizedResponseCode() {
        Mockito.when(userService.get(USER_NAME)).thenReturn(null);
        assertThat(controller.addComment(POST_ID, authentication, TEST_COMMENTARY)
                .getStatusCode().value(), is(401));
    }

}
