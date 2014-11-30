package zlogger.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.CRUDService;
import zlogger.util.CRUDTestUtilities;
import zlogger.util.TestUtilities;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class CRUDTest {

    private static final String UPDATED_TEST_MESSAGE = "updated message";
    private static final String UPDATED_PASSWORD = "updated password";
    private static final String TEST_CONTEXT = "testApplicationContext.xml";

    @Autowired
    private TestUtilities testUtilities;

    private CRUDService service;
    private Object createdObject;
    private Object retrievedId;
    private Object updatedObject;
    private Object deletedId;
    private TestContextManager testContextManager;

    @Parameters
    public static Collection<Object[]> data() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(TEST_CONTEXT);
        CRUDTestUtilities crudTestUtilities = (CRUDTestUtilities) ctx.getBean("crudTestUtilities");
        TestUtilities testUtilities = (TestUtilities) ctx.getBean("testUtilities");

        Post testPost = crudTestUtilities.getRealPost();
        Post updatedPost = testUtilities.getConstantPost();
        updatedPost.setMessage(UPDATED_TEST_MESSAGE);

        Commentary testCommentary = crudTestUtilities.getRealCommentary();
        Commentary updatedCommentary = testUtilities.getConstantCommentary();
        updatedCommentary.setMessage(UPDATED_TEST_MESSAGE);

        User testUser = crudTestUtilities.getRealUser();
        User updatedUser = testUtilities.getConstantUser();
        updatedUser.setPassword(UPDATED_PASSWORD);

        return Arrays.asList(new Object[][]{
                {crudTestUtilities.getPostService(), crudTestUtilities.createValidPost(), testPost.getId(),
                        updatedPost, testPost.getId()},
                {crudTestUtilities.getCommentaryService(), crudTestUtilities.createValidCommentary(),
                        testCommentary.getId(), updatedCommentary, testCommentary.getId()},
                {crudTestUtilities.getUserService(), crudTestUtilities.createValidUser(),
                        testUser.getUsername(), updatedUser, testUser.getUsername()}
        });
    }

    @Before
    public void setUpSpringContest() throws Exception {
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    public CRUDTest(CRUDService service, Object createdObject,
                    Object retrievedId, Object updatedObject, Object deletedId) {
        this.service = service;
        this.createdObject = createdObject;
        this.retrievedId = retrievedId;
        this.updatedObject = updatedObject;
        this.deletedId = deletedId;
    }

    @Test
    public void create() {
        Object id = service.add(createdObject);

        assertThat(id, notNullValue());
    }

    @Test
    public void retrieve() {
        Object retrievedObject = service.get(retrievedId);

        assertThat(retrievedObject, notNullValue());
    }

    @Test
    public void update() {
        Object id = service.update(updatedObject);

        assertThat(id, notNullValue());
    }

    @Test
    public void delete() {
        service.delete(deletedId);
        Object retrievedObject = service.get(deletedId);

        assertThat(testUtilities.isPersistent(retrievedObject), is(false));
    }

}
