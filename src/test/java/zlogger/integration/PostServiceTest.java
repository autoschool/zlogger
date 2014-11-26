package zlogger.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.PostService;
import zlogger.util.TestUtilities;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PostServiceTest {

    private static final String TEST_TITLE = "test";
    private static final String TEST_MESSAGE = "test";
    private static final String UPDATED_TEST_TITLE = "updated test";
    private static final String UPDATED_TEST_MESSAGE = "updated message";

    @Autowired
    private PostService postService;

    @Autowired
    private TestUtilities testUtilities;

    public Post getPost() {
        return postService.listPosts().get(1);
    }

    @Test
    public void shouldCreatePost() {
        Post post = new Post(TEST_TITLE, TEST_MESSAGE);
        User testUser = testUtilities.getConstantUser();
        Wall testWall = testUtilities.getConstantWall();
        Long id = postService.addPost(post, testWall, testUser);

        Post createdPost = postService.getPost(id);
        assertThat(createdPost.getTitle(), equalTo(TEST_TITLE));
        assertThat(createdPost.getMessage(), equalTo(TEST_MESSAGE));
        assertThat(createdPost.getCreator().getUsername(), equalTo(testUser.getUsername()));
        assertThat(createdPost.getWall().getWallId(), equalTo(testWall.getWallId()));
        assertThat(createdPost.getCreationDate(), notNullValue());
    }

    @Test
    public void shouldDeletePost() {
        Post post = getPost();
        postService.deletePost(post.getId());

        assertThat(testUtilities.isPersistent(post), is(false));
    }

    @Test
    public void shouldUpdatePost() {
        Post post = getPost();
        post.setTitle(UPDATED_TEST_TITLE);
        post.setMessage(UPDATED_TEST_MESSAGE);
        postService.updatePost(post);


        Post updatedPost = postService.getPost(post.getId());
        assertThat(updatedPost.getTitle(), equalTo(UPDATED_TEST_TITLE));
        assertThat(updatedPost.getMessage(), equalTo(UPDATED_TEST_MESSAGE));
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
        assertThat(gottenPost.getCommentaries(), notNullValue());
        assertThat(gottenPost.getCreator(), notNullValue());
    }

    @Test
    public void shouldGetPostsForUser() {
        User testUser = testUtilities.getConstantUser();
        List<Post> posts = postService.listPostsForUser(testUser);

        assertThat(posts, notNullValue());
    }

    @Test
    public void shouldGetPostsForWall() {
        Wall testWall = testUtilities.getConstantWall();
        List<Post> posts = postService.listPostsForWall(testWall);

        assertThat(posts, notNullValue());
    }

}
