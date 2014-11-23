package zlogger.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostContollerTest {
    private String baseUrl = "http://localhost:8080/";


    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();

    }

    @After
    public void down() {
        driver.quit();
    }

    @Test
    public void shouldViewPosts() {
        driver.get(baseUrl + "/list");

        //List<WebElement> elements = driver.findElements(By.className("posts"));
        WebElement element = driver.findElement(By.id("postsWall"));
        //Assert.assertNotNull("web server not started? ",elements);
//        Assert.assertTrue(elements.size() > 0);
//        for (int i = 0; i < elements.size(); i++) {
//            WebElement element = elements.get(i);
        String title = element.findElement(By.tagName("h3")).getText();
        System.out.println("Post title is: " + title);
//            Assert.assertNotNull("title is null. i="+i,title);
//        }
        assertThat(element, notNullValue());
    }
}
