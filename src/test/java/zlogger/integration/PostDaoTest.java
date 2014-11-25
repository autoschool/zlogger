package zlogger.integration;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dal.PostDao;
import zlogger.logic.dal.UserDao;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PostDaoTest {

    @Autowired
    private PostDao postDao;

    @Autowired
    private SessionFactory sessionFactory;

    private String testTitle = "test";
    private String testMessage = "test";
    private String updatedTestTitle = "updated test";
    private String updatedTestMessage = "updated message";
    private String testUserName = "testUser";
    private Long testWallId = 1L;

    @Mock
    private UserDao userDao;

    public PostDaoTest() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userDao.listUsers()).thenReturn(
                Arrays.asList(new User(testUserName))
        );
    }

    @Test
    @Transactional
    public void shouldGetPosts() {
        List<Post> posts = postDao.getPosts();
        assertThat(posts, notNullValue());
        assertThat(posts.isEmpty(), is(false));
    }

    public Post getPost() {
        return postDao.getPosts().get(0);
    }

    public User getUser() {
        return userDao.listUsers().get(0);
    }

    public Wall getWall() {
        Wall wall = new Wall();
        wall.setWallId(testWallId);
        return wall;
    }

    @Transactional
    @Test
    @Ignore("Broken")
    public void shouldCreatePost() {
        Post post = new Post(testTitle, testMessage);
        post.setCreator(getUser());
        post.setWall(getWall());
        post.setCreationDate(new Date());
        Long id = postDao.createPost(post);

        sessionFactory.getCurrentSession().flush();

        Post createdPost = postDao.getPostById(id);
        assertThat(createdPost.getTitle(), equalTo(testTitle));
        assertThat(createdPost.getMessage(), equalTo(testMessage));
        assertThat(createdPost.getCreator().getUsername(), equalTo(testUserName));
        assertThat(createdPost.getWall().getWallId(), equalTo(testWallId));
        assertThat(createdPost.getCreationDate(), notNullValue());
    }

    @Transactional
    @Test
    public void shouldDeletePost() {
        Post post = getPost();
        postDao.deletePostById(post.getId());

        sessionFactory.getCurrentSession().flush();

        assertThat(sessionFactory.getCurrentSession().contains(post),
                is(false));
    }

    @Test
    @Transactional
    @Ignore("Broken")
    public void shouldUpdatePost() {
        Post post = getPost();
        post.setTitle(updatedTestTitle);
        post.setMessage(updatedTestMessage);
        postDao.updatePost(post);

        sessionFactory.getCurrentSession().flush();

        Post updatedPost = postDao.getPostById(post.getId());
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
    @Transactional
    public void shouldGetPost() {
        Post post = getPost();

        Post gottenPost = postDao.getPostById(post.getId());
        assertThat(gottenPost, notNullValue());
    }
}