package zlogger.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.anything;
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

    @Test
    public void shouldViewPostAndCommentaries() {
        driver.get(baseUrl + "/list");
        WebElement posts = driver.findElement(By.id("postsWall"));
        List<WebElement> linksToPost = posts.findElements(By.tagName("h3"));

        String firstHref = linksToPost.iterator().next().findElement(By.tagName("a")).getAttribute("href");
        driver.get(firstHref);

        WebElement commentariesList = driver.findElement(By.className("commentariesList"));

        assertThat(commentariesList, anything());
    }

    @Test
    public void shouldViewCommentariesById() {
        long postId = 1;
        driver.get(baseUrl + "/post/"+postId+"/");

        WebElement commentariesList = driver.findElement(By.className("commentariesList"));

        assertThat(commentariesList, anything());
    }
}
