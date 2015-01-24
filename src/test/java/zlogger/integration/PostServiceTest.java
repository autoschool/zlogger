package zlogger.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zlogger.BasicTest;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.PostService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PostServiceTest extends BasicTest {

    @Autowired
    private PostService postService;

    @Test
    public void shouldGetPostsForUser() {
        User testUser = getUser();
        List<Post> posts = postService.list(testUser);

        assertThat(posts, notNullValue());
    }

}
