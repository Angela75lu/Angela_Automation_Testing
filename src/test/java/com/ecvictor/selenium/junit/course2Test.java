package com.ecvictor.selenium.junit;


import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class course2Test {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        String os = (System.getProperty("os.name"));

        if (os.equalsIgnoreCase("Mac OS X"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        else System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.just-eat.ca";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Summary: Login to just-eat success test.
     * @throws Exception
     */
    @Test
    public void test_login_success() throws Exception {
        driver.get(baseUrl + "/");
//        driver.findElement(By.xpath("/html/body/header/div[2]/nav/div/ul/li[1]/a")).click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,750)", "");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@href=\"/ideas/\"]")).click();

//        assertEquals("Login to your account at Just-Eat.ca", driver.getTitle());
//        try {
//            assertEquals("Signup", driver.findElement(By.linkText("Signup")).getText());
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//
//        //test sigin
//        driver.findElement(By.xpath("//*[@id=\"Email\"]")).clear();
//        driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("tewst");
//        driver.findElement(By.id("Password")).clear();
//        driver.findElement(By.id("Password")).sendKeys("test");
//
//        // ERROR: Caught exception [ERROR: Unsupported command [selectFrame |  | ]]
////        driver.findElement(By.cssSelector("div.recaptcha-checkbox-checkmark")).click();
//        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=async | ]]
//        Thread.sleep(3000);
//        for (int second = 0;; second++) {
//            if (second >= 60) fail("timeout");
//            try { if ("Untick this if you are using a shared device".equals(driver.findElement(By.cssSelector("div.control > p")).getText())) break; } catch (Exception e) {}
//            Thread.sleep(100);
//        }


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
