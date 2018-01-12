package com.ecvictor.testng.pinpineat.test_case;

import com.ecvictor.testng.pinpineat.page_object.HomePageMainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class LoginTest extends PinPinUtil{
    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
    }

    // Here we are calling the Data Provider object with its Name. either username or password is empty
    @DataProvider(name="InvalidCredentials")
    public static Object[][] Invalidcredentials() {

        return new Object[][] {{null,"bb"}, {"aa", null},{null, null}};
    }
    //  @Test (dataProvider="InvalidCredentials")
    public void loginButtonIsDisabled(String username, String password){
        String loginLink = HomePageMainMenu.loginLink;
        clickWhenReady(loginLink, 30, driver);
        WebElement loginBtn = driver.findElement(By.xpath(HomePageMainMenu.loginBtn));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        if(username!=null||password!=null){
            if(username!=null)
                driver.findElement(By.id("username")).sendKeys(username);
            if(password!=null)
                driver.findElement(By.id("password")).sendKeys(password);
            // System.out.println("Login button is "+isDisabled);
        }
        String isDisabled = loginBtn.getAttribute("disabled");
        Assert.assertEquals("true", isDisabled);
    }
    // Both username and password are not empty, but they are not valid
    @Test
    public void invalidLoginTest(){
        String loginLink = HomePageMainMenu.loginLink;
        clickWhenReady(loginLink, 30, driver);
        WebElement loginBtn = driver.findElement(By.xpath(HomePageMainMenu.loginBtn));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("a@b.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("bb");
        loginBtn.click();
        String invalidLogin =HomePageMainMenu.invalidLogin;
        // first method
        /*WebElement element = driver.findElement(By.xpath(invalidLogin));
        boolean result = validateLink(element, "Wrong username or password");
        if(result){
            System.out.println("Test passed");
        }*/
        //second method
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(invalidLogin)));
        Assert.assertEquals("Wrong username or password", webElement.getText());
    }

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { {"yanqing_lu@yahoo.com","alina123"}};
    }
    //Both username and password are correct
    // @Test(dataProvider="Authentication")
    public void validLoginTest(String username, String password){
        String loginLink = "//*[@id=\"myNavBar\"]/ul/li[1]/a/span/span[2]";
        clickWhenReady(loginLink, 30, driver);
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/div[2]/div/form/div[3]/button"));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        loginBtn.click();
        waitForPageLoad();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"myNavBar\"]/ul/li[2]"));
        String user = element.getAttribute("ng-show");
        Assert.assertEquals("loggedin", user);
    }

    //The Stay Logged In is checked
    //@Test(dataProvider = "Authentication")
    public void validLoginStayLoggedInChecked(String username, String password){
        String loginLink = "//*[@id=\"myNavBar\"]/ul/li[1]/a/span/span[2]";
        clickWhenReady(loginLink, 30, driver);
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/div[2]/div/form/div[3]/button"));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        loginBtn.click();
        waitForPageLoad();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"myNavBar\"]/ul/li[2]"));
        String user = element.getAttribute("ng-show");
        Assert.assertEquals("loggedin", user);
        // when Stay Logged In is clicked, the logged in will last for 2 more weeks.

      //  Cookie cookie = driver.manage().getCookieNamed("globals");
     //   System.out.println("Stay Logged In is Chosen");
       // System.out.println("cookie expiration date is " + cookie.getValue());

}

    @AfterClass
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
