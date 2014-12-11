package zlogger.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostContollerTest {
    private String baseUrl = "http://localhost:8080";


    private FirefoxDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void down() {
        driver.quit();
    }

    @Test
    public void shouldViewPosts() {
        driver.get(baseUrl + "/");

        WebElement element = driver.findElement(By.className("blog-post-title"));
        assertThat(element, notNullValue());

        //String title = element.getText();
        //assertThat(title, notNullValue());
    }
}
