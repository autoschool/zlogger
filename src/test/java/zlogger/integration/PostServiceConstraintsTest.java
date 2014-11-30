package zlogger.integration;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zlogger.logic.models.Post;
import zlogger.logic.models.Wall;
import zlogger.logic.services.PostService;
import zlogger.util.TestUtilities;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PostServiceConstraintsTest {

    private static final String TEST_TITLE = "test";
    private static final String TEST_MESSAGE = "test";

    @Autowired
    private PostService postService;

    @Autowired
    private TestUtilities testUtilities;

    @Test(expected = NullPointerException.class)
    public void shouldGetNullPointerExceptionForMalformedEntity() {
        Post post = new Post(TEST_TITLE, TEST_MESSAGE);
        postService.add(post, new Wall(), testUtilities.getConstantUser());
    }

    @Test
    public void shouldReturnEmptyListForNonexistentEntity() {
        Wall testWall = new Wall();
        testWall.setWallId(Long.MAX_VALUE);
        List<Post> posts = postService.listForWall(testWall);

        assertThat(posts, CoreMatchers.notNullValue());
        assertThat(posts.isEmpty(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForConstrainViolation() {
        Post post = new Post(TEST_TITLE, null);
        Long id = postService.add(post, testUtilities.getConstantWall(),
                testUtilities.getConstantUser());

        assertThat(id, nullValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForForeignKeyViolation() {
        Post post = new Post(TEST_TITLE, TEST_MESSAGE);
        Wall wall = new Wall();
        wall.setWallId(Long.MAX_VALUE);
        postService.add(post, wall, testUtilities.getConstantUser());
    }

}
