package com.ecvictor.testng.employee;

import com.ecvictor.selenium.cucumber.my_page_object.AdmissionSubMenu;
import com.ecvictor.selenium.cucumber.my_page_object.HomePageMainMenu;
import io.restassured.RestAssured;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;


public class PinpinEatTest {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();
        private Actions action;

    @BeforeClass
    public void setUp() throws Exception {
            Properties prop = getProperties();
            String browserType = prop.getProperty("browserName");
            System.out.println("Current Browser Type is " + browserType);
            if(browserType.equals("Chrome")) {

                String os = (System.getProperty("os.name"));

                if (os.equalsIgnoreCase("Mac OS X"))
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                else System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--kiosk");
                driver = new ChromeDriver(chromeOptions);

                            }
            else if(browserType.equals("Firefox")) {
               // System.setProperty("webdriver.gecko.driver", "E:\\Automation_Testing\\pinpineat_test\\download\\geckodriver-v0.19.1-win64\\geckodriver.exe");
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driver=new FirefoxDriver();
            }
            action = new Actions(driver);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            baseUrl = "http://www.pinpineat.com";
            driver.get(baseUrl + "/");
        }

    @Test
    public void TestPinPinEatAvailability() throws Exception{
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.pinpineat.com/#!/");
    }

    @Test
    public void testHomeAddressTitle() throws Exception {
        assertEquals("Pinpin Eat", driver.getTitle());
    }

    @Test
    public void headLogoIsPresent() throws Exception{
        String headerLogo = "//*[@id=\"headerlogo\"]";
        boolean isPresent = isElementPresent(By.xpath(headerLogo));
        Assert.assertTrue(isPresent, "the headerLogo is there");
    }
    @Test
    public void shoppingCartIsPresent() throws Exception{
        String shoppingCart = "//*[@id=\"myNavBar\"]/ul/li[3]/a/i";
        boolean isPresent = isElementPresent(By.xpath(shoppingCart));
        Assert.assertTrue(isPresent, "the headerLogo is there");
    }

    @Test
    public void testLoginLink() throws Exception {
              // String loginLink = "//div[@id=\'myNavBar\']/ul/li[4]/a/span";
        String loginLink = "//*[@id=\"myNavBar\"]/ul/li[1]/a/span/span[2]";
        clickWhenReady(loginLink, 30, driver);
        WebElement loginSlogan = driver.findElement(By.xpath("//*[@id=\"loginSlogan\"]"));
        Assert.assertEquals("Best asian food In Montreal city, eat yummy food!",loginSlogan.getText());


        /*String englishLink ="//*[@id=\"myNavBar\"]/ul/li[4]/ul/li[1]/a";
        clickWhenReady(englishLink, 30, driver);


        // the following test the language button
        WebElement languageElement = driver.findElement(By.cssSelector(".bordered"));
        String languageBtnTxt = languageElement.getText();
        Assert.assertEquals("Language", languageBtnTxt);

        // the following check the english button\\

        Actions actions = new Actions(driver);
        actions.moveToElement(languageElement).perform();
        WebElement engElement = driver.findElement(By.linkText("English"));
        actions.moveToElement(engElement);
        actions.click().build().perform();
        languageBtnTxt = languageElement.getText();
        Assert.assertEquals("Language", languageBtnTxt);
        String mainSlogan = driver.findElement(By.cssSelector("#mainSlogan")).getText();
        Assert.assertEquals("Are you hungry?", mainSlogan);*/

    }
  //  @Test
    public void checkLanguageSpelling(){
        WebElement languageElement = driver.findElement(By.cssSelector(".bordered"));
        String languageBtnTxt = languageElement.getText();
        Assert.assertEquals("Language", languageBtnTxt);
    }
    @Test
    // this test result should be English, Français, 中文
    public void checkLanguageTypes(){
        WebElement languageElement = driver.findElement(By.cssSelector(".bordered"));
        action.moveToElement(languageElement).click().build().perform();
      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"myNavBar\"]/ul/li[4]/ul/li/a"));
      for (WebElement element : elements) {
            System.out.println(element.getText());
      }
    }

    @AfterClass
    public void tearDown() throws Exception {
            driver.close();
            //Firefox doesn't work for driver.quit();
            //driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
    }

    private boolean isElementPresent(By by) {
        try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

    public static void clickWhenReady(String link, int timeout,WebDriver driver) {

        WebElement element;

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(link)));

        element.click();

    }

    public static Properties getProperties() {
        //load properties from the config file
        String resourceName = "config.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try {
            InputStream resourceStream = loader.getResourceAsStream(resourceName);
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }


