package zlogger.unit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.models.Wall;
import zlogger.logic.services.impl.PostServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl testingObject;

    @Mock
    private PostDao postDao;

    @Mock
    private User testUser;
    @Mock
    private Wall testWall;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(testUser.getUsername()).thenReturn("");
        Mockito.when(testUser.getEnabled()).thenReturn(true);
        Mockito.when(testWall.getWallId()).thenReturn(0L);
    }

    @Test
    public void should_createPost() {
        //Given
        Post post = new Post("testTitle", "custom message \n\n\t dsg");
        Long testId = 22L;
        Mockito.when(postDao.create(post)).thenReturn(testId);

        //When
        Long newId = testingObject.add(post, testWall, testUser);

        //Then
        assertThat(testId, equalTo(newId));
    }

    @Test
    public void should_listPosts() {
        //Given
        List<Post> dbPosts = new ArrayList<>();

        Post db1 = new Post();
        db1.setId(1L);
        db1.setTitle("testTitle1");
        db1.setMessage("testTitle2");
        db1.setCreationDate(new Date());
        dbPosts.add(db1);

        Post db2 = new Post();
        db2.setId(2L);
        db2.setTitle("testTitle2");
        db2.setMessage("testTitle2");
        db2.setCreationDate(new Date());
        dbPosts.add(db2);

        Mockito.when(postDao.list()).thenReturn(dbPosts);

        //When
        List<Post> posts = testingObject.list();

        //Then
        assertThat(posts, containsInAnyOrder(dbPosts.toArray()));
    }
}
