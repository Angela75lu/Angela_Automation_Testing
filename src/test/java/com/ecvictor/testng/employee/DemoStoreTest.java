package com.ecvictor.testng.employee;

/**
 * Created by ccao on 2017-04-29.
 */

import java.awt.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class DemoStoreTest {

    private static WebDriver driver;

   @BeforeMethod
    public static void setUp(){
       String chromeDriverLocation ="src/test/resources/drivers/chromedriver.exe";
       System.out.println("chromeDriverLocation"+chromeDriverLocation);
       System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
       driver = new ChromeDriver();
   }

   @DataProvider(name = "Authentication")
    public static Object[][] credentials() {

        return new Object[][]{{"testuser_1", "Test@123"}, {"testuser_2", "Test@123"}};

    }
    // Here we are calling the Data Provider object with its Name

    @Test(dataProvider = "Authentication")

    public void demoStoreTest(String sUsername, String sPassword) {

       // String chromeDriverLocation = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver";
        //System.out.println("chromeDriverLocation"+chromeDriverLocation);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://store.demoqa.com/");

        driver.findElement(By.xpath(".//*[@id='account']/a")).click();

        driver.findElement(By.id("log")).sendKeys(sUsername);

        driver.findElement(By.id("pwd")).sendKeys(sPassword);

        driver.findElement(By.id("login")).click();

      //  driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
        //Assert.assertEquals(driver.findElement(By.cssSelector("p.response")).getText(), "^ERROR: The password you entered for the username " + sUsername + " is incorrect. Lost your password?$");
        WebElement element = null;

        WebDriverWait wait = new WebDriverWait(driver, 30);

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ajax_loginform']/p[@class=\"response\"]")));
        Assert.assertEquals(element.getText(),"ERROR: The password you entered for the username " + sUsername + " is incorrect. Lost your password?" );

    }
   @AfterMethod
    public static void tearDown(){
        driver.quit();
    }

}