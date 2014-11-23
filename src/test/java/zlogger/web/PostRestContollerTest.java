package zlogger.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

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

/**
 * Created by alexwyrm on 11/19/14.
 */
public class PostRestContollerTest {

    private WebTarget target;
    private Long id;
    private User testUser;
    private Wall testWall;

    private String baseUrl = "http://localhost:8080/posts";
    private String testTitle = "test";
    private String testMessage = "test message";
    private String updatedTitle = "updated test";
    private String updatedMessage = "updated test message";

    public PostRestContollerTest() {
        testUser = new User();
        testUser.setUserId(new Long(1));
        testWall = new Wall();
        testWall.setWallId(new Long(1));
    }

    @Before
    public void prepareClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient(clientConfig);

        target = client.target(baseUrl);
    }

    @Step
    public void shouldCreatePost() {
        Post post = new Post(testTitle, testMessage);
        post.setCreator(testUser);
        post.setWall(testWall);
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
        Post post = new Post(updatedTitle, updatedMessage);
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
        shouldGetPost(testTitle, testMessage);
        shouldUpdatePost();
        shouldGetPost(updatedTitle, updatedMessage);
        shouldDeletePost();
    }

}
