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
import zlogger.logic.services.GenericService;
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

    private static final String UPDATED_TEST_TITLE = "updated title";
    private static final String UPDATED_TEST_MESSAGE = "updated message";
    private static final String UPDATED_PASSWORD = "updated password";
    private static final String TEST_CONTEXT = "testApplicationContext.xml";

    @Autowired
    private TestUtilities testUtilities;

    private GenericService service;
    private Object createdObject;
    private Object retrievedId;
    private Object updatedObject;
    private Object deletedObject;
    private Object invalidObject;
    private TestContextManager testContextManager;

    @Parameters
    public static Collection<Object[]> data() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(TEST_CONTEXT);
        CRUDTestUtilities crudTestUtilities = (CRUDTestUtilities) ctx.getBean("crudTestUtilities");
        TestUtilities testUtilities = (TestUtilities) ctx.getBean("testUtilities");

        Post testPost = crudTestUtilities.getRealPost();
        Post updatedPost = testUtilities.getConstantPost();
        updatedPost.setMessage(UPDATED_TEST_MESSAGE);
        updatedPost.setTitle(UPDATED_TEST_TITLE);
        Post invalidPost = crudTestUtilities.createValidPost();
        invalidPost.setMessage(null);

        Commentary testCommentary = crudTestUtilities.getRealCommentary();
        Commentary updatedCommentary = testUtilities.getConstantCommentary();
        updatedCommentary.setMessage(UPDATED_TEST_MESSAGE);
        Commentary invalidCommentary = crudTestUtilities.createValidCommentary();
        invalidCommentary.setMessage(null);

        User testUser = crudTestUtilities.getRealUser();
        User updatedUser = testUtilities.getConstantUser();
        updatedUser.setPassword(UPDATED_PASSWORD);
        User invalidUser = crudTestUtilities.createValidUser();
        invalidUser.setPassword(null);

        return Arrays.asList(new Object[][]{
                {crudTestUtilities.getPostService(), crudTestUtilities.createValidPost(), updatedPost.getId(),
                        updatedPost, testPost, invalidPost},
                {crudTestUtilities.getCommentaryService(), crudTestUtilities.createValidCommentary(),
                        updatedCommentary.getId(), updatedCommentary, testCommentary, invalidCommentary},
                {crudTestUtilities.getUserService(), crudTestUtilities.createValidUser(),
                        updatedUser.getUsername(), updatedUser, testUser, invalidUser}
        });
    }

    @Before
    public void setUpSpringContest() throws Exception {
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    public CRUDTest(GenericService service, Object createdObject,
                    Object retrievedId, Object updatedObject, Object deletedObject,
                    Object invalidObject) {
        this.service = service;
        this.createdObject = createdObject;
        this.retrievedId = retrievedId;
        this.updatedObject = updatedObject;
        this.deletedObject = deletedObject;
        this.invalidObject = invalidObject;
    }

    @Test
    public void shouldCreate() {
        Object id = service.add(createdObject);

        assertThat(id, notNullValue());
    }

    @Test
    public void shouldRetrieve() {
        Object retrievedObject = service.get(retrievedId);

        assertThat(retrievedObject, notNullValue());
    }

    @Test
    public void shouldUpdate() {
        Object id = service.update(updatedObject);

        assertThat(id, notNullValue());
    }

    @Test
    public void shouldDelete() {
        service.delete(deletedObject);

        assertThat(testUtilities.isPersistent(deletedObject), is(false));
    }

    //todo fix exception inheritance
    @Test(expected = RuntimeException.class)
    public void shouldFail() {
        service.add(invalidObject);
    }

}
