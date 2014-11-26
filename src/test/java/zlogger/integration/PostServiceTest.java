package zlogger.integration;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.PostService;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by alexwyrm on 11/26/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PostServiceTest {

    private String testTitle = "test";
    private String testMessage = "test";
    private String updatedTestTitle = "updated test";
    private String updatedTestMessage = "updated message";
    private String testUserName = "testUser";
    private Long testWallId = 1L;

    @Autowired
    private PostService postService;

    public Post getPost() {
        return postService.listPosts().get(0);
    }

    public User getUser() {
        return new User(testUserName);
    }

    public Wall getWall() {
        Wall wall = new Wall();
        wall.setWallId(testWallId);
        return wall;
    }

    @Test
    public void shouldCreatePost() {
        Post post = new Post(testTitle, testMessage);
        post.setCreator(getUser());
        post.setWall(getWall());
        post.setCreationDate(new Date());
        Long id = postService.addPost(post);

        Post createdPost = postService.getPost(id);
        assertThat(createdPost.getTitle(), equalTo(testTitle));
        assertThat(createdPost.getMessage(), equalTo(testMessage));
        assertThat(createdPost.getCreator().getUsername(), equalTo(testUserName));
        assertThat(createdPost.getWall().getWallId(), equalTo(testWallId));
        assertThat(createdPost.getCreationDate(), notNullValue());
    }

    @Test
    @Ignore("Needs implementing Session.contains() in service or session purging")
    public void shouldDeletePost() {
        Long id = getPost().getId();
        postService.deletePost(id);

        assertThat(id, nullValue());
    }

    @Test
    public void shouldUpdatePost() {
        Post post = getPost();
        post.setTitle(updatedTestTitle);
        post.setMessage(updatedTestMessage);
        postService.updatePost(post);


        Post updatedPost = postService.getPost(post.getId());
        assertThat(updatedPost.getTitle(), equalTo(updatedTestTitle));
        assertThat(updatedPost.getMessage(), equalTo(updatedTestMessage));
        assertThat(updatedPost.getCreator().getUsername(),
                equalTo(post.getCreator().getUsername()));
        assertThat(updatedPost.getWall().getWallId(),
                equalTo(post.getWall().getWallId()));
        assertThat(updatedPost.getCreationDate(),
                equalTo(post.getCreationDate()));
    }

    @Test
    public void shouldGetPost() {
        Post post = getPost();

        Post gottenPost = postService.getPost(post.getId());
        assertThat(gottenPost, notNullValue());
    }

}
