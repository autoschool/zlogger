package zlogger.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yandex.qatools.allure.annotations.Step;
import zlogger.logic.models.Post;
import zlogger.util.TestUtilities;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Ignore("Outdated")
public class PostRestControllerTest {

    @Autowired
    TestUtilities testUtilities;

    private WebTarget target;
    private Long id;

    private static final String BASE_URL = "http://localhost:8080/posts";
    private static final String TEST_TITLE = "test";
    private static final String TEST_MESSAGE = "test message";
    private static final String UPDATED_TITLE = "updated test";
    private static final String UPDATED_MESSAGE = "updated test message";

    @Before
    public void prepareClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient(clientConfig);

        target = client.target(BASE_URL);
    }

    @Step
    public void shouldCreatePost() {
        Post post = new Post(TEST_TITLE, TEST_MESSAGE);
        post.setCreator(testUtilities.getConstantUser());
        post.setWall(testUtilities.getConstantWall());
        Response response = target
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Content-type", "application/json")
                .post(Entity.json(post));

        assertThat(response.getStatus(), is(201));
        id = response.readEntity(new GenericType<Long>() {
        });
    }

    @Step
    public void shouldGetPost(String title, String message) {
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus(), is(200));

        Post post = response.readEntity(Post.class);
        assertThat(post.getTitle(), equalTo(title));
        assertThat(post.getMessage(), equalTo(message));
    }

    @Step
    public void shouldUpdatePost() {
        Post post = new Post(UPDATED_TITLE, UPDATED_MESSAGE);
        post.setId(id);
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Content-type", "application/json")
                .put(Entity.json(post));

        assertThat(response.getStatus(), is(200));
    }

    @Step
    public void shouldDeletePost() {
        Response response = target
                .request()
                .delete();

        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void shouldBeNonempty() {
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus(), is(200));

        List<Post> posts = response.readEntity(
                new GenericType<List<Post>>() {
                });
        assertThat(posts.size(), not(is(0)));
    }

    @Test
    public void CRUDTest() throws JsonProcessingException {
        shouldCreatePost();
        target = target.path("/" + id);
        shouldGetPost(TEST_TITLE, TEST_MESSAGE);
        shouldUpdatePost();
        shouldGetPost(UPDATED_TITLE, UPDATED_MESSAGE);
        shouldDeletePost();
    }

}
