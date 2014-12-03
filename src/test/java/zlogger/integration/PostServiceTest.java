package zlogger.integration;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.PostService;
import zlogger.util.TestUtilities;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private TestUtilities testUtilities;

    @Test
    public void shouldGetPostsForUser() {
        User testUser = testUtilities.getConstantUser();
        List<Post> posts = postService.listForUser(testUser);

        assertThat(posts, notNullValue());
    }

    @Test
    public void shouldGetPostsForWall() {
        Wall testWall = testUtilities.getConstantWall();
        List<Post> posts = postService.listForWall(testWall);

        assertThat(posts, notNullValue());
    }


    @Test
    public void shouldGetCommentariesForPost() {
        //Given
        Post post = postService.list().get(0);
        //When get Commentaries
        Set<Commentary> postCommentaries = post.getCommentaries();
        //Then it is not content of Posts
        Commentary commentary = postCommentaries.iterator().next();
        assertThat("test is incorrect", post.getId(), equalTo(commentary.getId()));
        assertThat("post.getCommentaries possible returns posts", post.getMessage(), not(equalToIgnoringCase(commentary.getMessage())));
    }

}
