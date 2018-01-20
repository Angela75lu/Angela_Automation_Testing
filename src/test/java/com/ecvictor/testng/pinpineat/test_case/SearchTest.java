package com.ecvictor.testng.pinpineat.test_case;

import com.ecvictor.testng.pinpineat.page_object.HomePageMainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.ecvictor.testng.pinpineat.page_object.HomePageMainMenu.*;

public class SearchTest extends PinPinUtil {

    @BeforeClass
    public void setUp() throws Exception{
        super.setUp();
    }

    @Test (alwaysRun=true)
    public void searchRestaurant(){
        elmentIsVisible(HomePageMainMenu.searchTxt, "2", 30, driver);
       WebElement search = driver.findElement(By.cssSelector(HomePageMainMenu.searchTxt));
       WebElement searchBtn = driver.findElement(By.xpath(HomePageMainMenu.searchBtn));
       if(search.getText().isEmpty()){
            String disable = searchBtn.getAttribute("disabled");
            Assert.assertEquals("true", disable);
       }
        search.clear();
        search.sendKeys("H9K1P2");
        searchBtn.click();
        waitForPageLoad();
        WebElement firstRest = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[1]/div/div/a/div[2]/span[2]/b"));
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div/div/a/div[2]/span[2]/b"));
        System.out.println("There are total "+list.size()+" Restaurants are found.");
    }

    @AfterClass
    public void tearDown() throws Exception{
        super.tearDown();
    }
}
