package zlogger.web;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver_PostContollerTest {
    private String baseUrl = "http://localhost:8080/";


    private WebDriver driver;

    @org.junit.Before
    public void setup()
    {
        driver = new FirefoxDriver();

    }

    @org.junit.After
    public void down()
    {
        driver.quit();
    }

    @Test
    public void should_viewPosts() {
        //Given

        //When
        driver.get(baseUrl + "/lists");

        //Then
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
        Assert.assertNotNull(element);
    }
}
