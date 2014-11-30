package zlogger.web;

import org.junit.After;
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


    private WebDriver driver;

    @Before
    public void setup() {
        driver = new PhantomJSDriver();

    }

    @After
    public void down() {
        driver.quit();
    }

    @Test
    public void shouldViewPosts() {
        driver.get(baseUrl + "/list");

        WebElement element = driver.findElement(By.id("postsWall"));
        String title = element.findElement(By.tagName("h3")).getText();
        assertThat(element, notNullValue());
        assertThat(title, notNullValue());
    }
}
