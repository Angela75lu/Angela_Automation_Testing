package com.ecvictor.testng.pinpineat.test_case;

import com.ecvictor.testng.pinpineat.Page_Module.CheckoutAction;
import com.ecvictor.testng.pinpineat.page_object.CheckoutInfo;
import com.ecvictor.testng.pinpineat.page_object.HomePageMainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;


public class OrderTest extends PinPinUtil {
    CheckoutAction checkoutAction = null;

   @BeforeClass
    public void setUp() throws Exception{
        super.setUp();
    }

    //Both username and password are correct
   @Test()
    public void validLoginTest(){
        String loginLink = "//*[@id=\"myNavBar\"]/ul/li[1]/a/span/span[2]";
        clickWhenReady(loginLink, 30, driver);
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/div[2]/div/form/div[3]/button"));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("yanqing_lu@yahoo.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("alina123");
        loginBtn.click();
        waitForPageLoad();
    }


    @Test(alwaysRun=true, dependsOnMethods={"validLoginTest"})
    public void orderTest() {
        orderBeforeCheckout();
        String checkoutBtn = HomePageMainMenu.checkoutBtn;
        elmentIsVisible(checkoutBtn, "1", 30, driver);
        //populate the check out info
        driver.findElement(By.xpath(checkoutBtn)).click();
        try{
            Thread.sleep(3000);
        } catch(Exception e){

        }
        checkoutAction = new CheckoutAction(driver);
        elmentIsVisible(CheckoutInfo.street, "1", 30, driver);
        populateData();
    }


 //   @Test(alwaysRun=true, dependsOnMethods={"validLoginTest"})
    public void TestCheckOut(){
        orderBeforeCheckout();
        String orderBtn = HomePageMainMenu.orderBtn;
        elmentIsVisible(orderBtn, "1", 30, driver);
        driver.findElement(By.xpath(orderBtn)).click();

    }

    public void orderBeforeCheckout(){
        elmentIsVisible(HomePageMainMenu.searchTxt, "1", 30, driver);
        WebElement search = driver.findElement(By.xpath(HomePageMainMenu.searchTxt));
        WebElement searchBtn = driver.findElement(By.xpath(HomePageMainMenu.searchBtn));
        if (search.getText().isEmpty()) {
            String disable = searchBtn.getAttribute("disabled");
            Assert.assertEquals("true", disable);
        }
        search.clear();
        search.sendKeys("H9K1P2");
        searchBtn.click();
        waitForPageLoad();
        String parentWin = driver.getWindowHandle();
        // System.out.println("parentwin is "+parentWin);

        WebElement rest = driver.findElement(By.xpath(HomePageMainMenu.restaurant));
        rest.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switchToNewWindow(parentWin);
        clickWhenReady(HomePageMainMenu.order1, 30, driver);
        clickWhenReady(HomePageMainMenu.order2, 30, driver);
        // click the check out basket
        elmentIsVisible(HomePageMainMenu.checkoutLink, "1", 30, driver);
        driver.findElement(By.xpath(HomePageMainMenu.checkoutLink)).click();

        System.out.println("Check out link is clicked");
        // waitForPageLoad();
        String orderTotal = HomePageMainMenu.orderTotal;
        elmentIsVisible(orderTotal, "1", 30, driver);
        String orderTotalTxt = driver.findElement(By.xpath(orderTotal)).getText();
        System.out.println("the total is "+ orderTotalTxt);
        driver.close();
        driver.switchTo().window(parentWin);

    }
    private void populateData(){
        String street = "18392 Rue Amalfi";
        String city = "Pierrefonds";
        String postCode = "H9K1P2";
        checkoutAction.populateDelivery(street, city, postCode);
        isClickable(driver, checkoutAction.deliveryNext());
        checkoutAction.deliveryNext().click();
        String firstname = "Mary";
        String lastname = "Johnson";
        String phone = "5146579112";
        String email = "bb@yahoo.com";
        checkoutAction.populateUserDetails(firstname, lastname, phone, email);
        isClickable(driver, checkoutAction.userDetailsBtn());
        checkoutAction.userDetailsBtn().click();
        String cardHolder = "Mary Johnson";
        String cardNum = "1234 3211 1234 4566";
        String expMon = "11";
        String expYear = "2017";
        String cvc = "234";
        checkoutAction.populatePayment(cardHolder, cardNum, expMon, expYear, cvc);
    }

    @AfterClass
    public void tearDown() throws Exception{
        super.tearDown();
    }
}

