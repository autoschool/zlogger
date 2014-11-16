package ru.yandex.autoschool.zlogger.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by alexwyrm on 11/15/14.
 */
public class GetTest {

    private String baseUrl = "localhost:8080";
    private WebDriver driver;

    @Before
    public void prepareDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void shouldProperlyLoad() {
        driver.get(baseUrl);
        WebElement title = driver.findElement(By.className("blog-title"));

        assertThat("Root page is not loaded", title, notNullValue());
        assertThat("Root page is malformed", title.getText(), equalTo("Zlogger"));
    }

    @After
    public void closeDriver() {
        driver.close();
    }

}
