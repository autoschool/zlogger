package zlogger.unit;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import zlogger.logic.dao.PostDao;
import zlogger.logic.models.Post;
import zlogger.logic.services.impl.PostServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl testingObject;

    @Mock
    private PostDao postDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_createPost() {
        //Given
        Post post = new Post("testTitle", "custom message \n\n\t dsg");
        Long testId = Long.valueOf(22);
        Mockito.when(postDao.createPost(post)).thenReturn(testId);

        //When
        Long newId = testingObject.addPost(post);

        //Then
        Assert.assertEquals(testId, newId);
    }

    @Test
    public void should_setDate_createPost() {
        //Given
        Post post = mock(Post.class);
        //When
        Long newId = testingObject.addPost(post);
        //Then
        verify(post, times(1)).setCreationDate(any(Date.class));
    }

    @Test
    public void should_listPosts() {
        //Given
        List<Post> dbPosts = new ArrayList<Post>();

        Post db1 = new Post();
        db1.setId(Long.valueOf(1));
        db1.setTitle("testTitle1");
        db1.setMessage("testTitle2");
        db1.setCreationDate(new Date());
        dbPosts.add(db1);

        Post db2 = new Post();
        db2.setId(Long.valueOf(2));
        db2.setTitle("testTitle2");
        db2.setMessage("testTitle2");
        db2.setCreationDate(new Date());
        dbPosts.add(db2);

        Mockito.when(postDao.getPosts()).thenReturn(dbPosts);

        //When
        List<Post> posts = testingObject.listPosts();

        //Then
        assertThat(posts, containsInAnyOrder(dbPosts.toArray()));
    }
}
