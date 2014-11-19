package zlogger.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by alexwyrm on 11/19/14.
 */
public class PostServiceTest {

    private String baseUrl = "http://localhost:8080";
    private String testTitle = "test";
    private String testMessage = "test";

    @Step
    public void create() {
    }

    @Test
    public void CRUDTest() throws JsonProcessingException {
        create();
    }

}
