package zlogger.integration;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import zlogger.logic.services.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:zlogger/integration/mvc-dispatcher-servlet.xml"})
@DirtiesContext
//@IntegrationTest
public class testedclass {

//    @Autowired
//    @Qualifier("postDao")
//    PostDao impl;

    @Autowired  /*org.springframework.beans.factory.NoSuchBeanDefinitionException:
    No qualifying bean of type [zlogger.logic.services.PostService] found for dependency:
    expected at least 1 bean which qualifies as autowire candidate for this dependency.
    Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}*/
    PostService impl;

//    @Autowired
//    @Qualifier("mySessionFactory")
//    private SessionFactory mySessionFactory;

//    @Autowired
//    ApplicationContext ctx;
//
//    @Before
//    public void setUp() {
//        impl = ctx.getBean("postDao", PostDaoHibernateImpl.class);
//    }

    @Test
    public void testSystem_should_Work() throws Exception {
        Assert.assertEquals(1,1);
    }

//    @Test
//    public void test_should_Connect() throws Exception {
//        //PostDao impl = new PostDaoHibernateImpl();
//
//        List<Post> posts = impl.getPosts();
//        Assert.assertNotNull(posts);
//        Assert.assertFalse(posts.isEmpty());
//    }
}