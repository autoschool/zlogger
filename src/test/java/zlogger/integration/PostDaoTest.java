package zlogger.integration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PostDaoTest {

    @Autowired
    private PostDao postDao;

    @Test
    public void shouldGetPosts() {
        List<Post> posts = postDao.getPosts();
        assertThat(posts, notNullValue());
        assertThat(posts.get(0), notNullValue());
    }

}