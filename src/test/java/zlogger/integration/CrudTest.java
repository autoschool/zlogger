package zlogger.integration;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import zlogger.BasicTest;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.GenericService;
import zlogger.logic.services.PostService;
import zlogger.logic.services.UserService;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
public class CrudTest extends BasicTest {

    private static final String TEST_CONTEXT = "applicationContext.xml";

    private GenericService service;

    public CrudTest(GenericService service) {
        this.service = service;
    }

    @Parameters
    public static Collection<Object[]> data() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(TEST_CONTEXT);
        CommentaryService commentaryService = (CommentaryService) ctx.getBean("commentaryService");
        PostService postService = (PostService) ctx.getBean("postService");
        UserService userService = (UserService) ctx.getBean("userService");

        return Arrays.asList(new Object[][]{
                {commentaryService},
                {postService},
                {userService}
        });
    }

    @Before
    public void setUpSpringContest() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    @Test
    @FlywayTest
    public void shouldCreate() {
        Object id = service.add(getValidObject(service));

        assertThat(id, notNullValue());
    }

    @Test
    @FlywayTest
    public void shouldRetrieve() {
        Object retrievedObject = getFirst(service);

        assertThat(retrievedObject, notNullValue());
    }

    @Test
    @FlywayTest
    public void shouldUpdate() {
        Object object = getFirst(service);
        updateObject(service, object);
        Object id = service.update(object);

        assertThat(id, notNullValue());
    }

    @Test
    @FlywayTest
    public void shouldDelete() {
        Object deletedObject = getFirst(service);
        service.delete(deletedObject);

        assertThat(isPersistent(deletedObject), is(false));
    }

    //todo fix exception inheritance
    @Test(expected = RuntimeException.class)
    @FlywayTest
    public void shouldFail() {
        service.add(getInvalidObject(service));
    }


}
