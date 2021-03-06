package zlogger.web;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostContollerTest {

    private String baseUrl = "http://localhost:8080";
    private WebDriver driver = new PhantomJSDriver();

    @Before
    public void setup() {
        driver.get(baseUrl);
    }

    @Test
    public void shouldViewPosts() {
        WebElement element = driver.findElement(By.className("post"));
        assertThat(element, notNullValue());
    }
}
