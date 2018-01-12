package com.ecvictor.testng.pinpineat.test_case;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.function.Function;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;


public class HomePageTest extends PinPinUtil {

    private Actions action;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void TestPinPinEatAvailability() throws Exception{
       //ystem.out.println("in testing..."+driver.getCurrentUrl());
       Assert.assertEquals(driver.getCurrentUrl(), "https://www.pinpineat.com/#!/");
    }
    //@Test
    public void testHomeAddressTitle() throws Exception {
        assertEquals("Pinpin Eat", driver.getTitle());
    }

    //@Test
    public void headLogoIsPresent() throws Exception{
        String headerLogo = "//*[@id=\"headerlogo\"]";
        boolean isPresent = isElementPresent(By.xpath(headerLogo));
        Assert.assertTrue(isPresent, "the headerLogo is there");
    }
    //@Test
    public void shoppingCartIsPresent() throws Exception{
        String shoppingCart = "//*[@id=\"myNavBar\"]/ul/li[3]/a/i";
        boolean isPresent = isElementPresent(By.xpath(shoppingCart));
        Assert.assertTrue(isPresent, "the headerLogo is there");
    }

    //@Test
    public void testLoginLink() throws Exception {
              // String loginLink = "//div[@id=\'myNavBar\']/ul/li[4]/a/span";
        String loginLink = "//*[@id=\"myNavBar\"]/ul/li[1]/a/span/span[2]";
        clickWhenReady(loginLink, 30, driver);
        WebElement loginSlogan = driver.findElement(By.xpath("//*[@id=\"loginSlogan\"]"));
        Assert.assertEquals("Best asian food In Montreal city, eat yummy food!",loginSlogan.getText());
    }
  //  @Test
    public void checkLanguageSpelling(){
        WebElement languageElement = driver.findElement(By.cssSelector(".bordered"));
        String languageBtnTxt = languageElement.getText();
        Assert.assertEquals("Language", languageBtnTxt);
    }
   // @Test
    // this test result should be English, Français, 中文
    public void checkLanguageTypes(){
        WebElement languageElement = driver.findElement(By.cssSelector(".bordered"));
        action = new Actions(driver);
        action.moveToElement(languageElement).click().build().perform();
      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"myNavBar\"]/ul/li[4]/ul/li/a"));
      for (WebElement element : elements) {
            System.out.println(element.getText());
      }
    }

    //@Test(priority=1)
    public void checkShoppingCartSignInPopup() {
        WebElement shopCart = driver.findElement(By.xpath("//*[@id=\"myNavBar\"]/ul/li[3]/a/i"));

        String master = driver.getWindowHandle();
        shopCart.click();
        try{
            //Wait 10 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 200);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals("Please sign in or sign up first!", alert.getText());
            alert.accept();
        }catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
        }

    }
   // @Test
    public void checkMapSearchInputAvailability(){

        if(isElementPresent(By.cssSelector("#mapsearchInput"))){
            System.out.println("The map search input is available");
        }
    }
    //@Test
    public void checkSearchButton(){
        if(isElementPresent(By.cssSelector("//*[@id=\"mapsearchbtn\"]"))){
            System.out.println("The restaurant search button is available");
        }
    }

    //@Test
    public void downloadAppleStoreLink(){
        if(isElementPresent(By.xpath("//*[@id=\"appstoreImg\"]"))){
            System.out.println("The Apple Store download link is there");
        }
    }

    //@Test
    public void googlePlayLink(){
        if(isElementPresent(By.xpath("//*[@id=\"playstoreImg\"]"))){
            System.out.println("The Google play link is there");
        }
    }

  // @Test
    public void driverImageCheck(){
        if(isElementPresent(By.xpath("//*[@id=\"separatorimg\"]"))){
            System.out.println("Driver Image is Displayed");
        }
    }

   // @Test
    public void deliveryInformation(){
        WebElement deliveryInfo = driver.findElement(By.xpath("//*[@id=\"homeinforow\"]/div/div[1]/p"));
        String deliveryContent = deliveryInfo.getText();
        Assert.assertTrue(deliveryContent.contains("Fast delivery"));
    }


    //@Test
    public void favoriteLocalStore(){
        WebElement favoriteStore = driver.findElement(By.xpath("//*[@id=\"homeinforow\"]/div/div[2]/p"));
        String deliveryContent = favoriteStore.getText();
        Assert.assertTrue(deliveryContent.contains("Your favorite local stores"));
    }

   // @Test
    public void customerService(){
        WebElement custService = driver.findElement(By.xpath("//*[@id=\"homeinforow\"]/div/div[3]/p/em"));
        String custServiceText = custService.getText();
       // System.out.println("custServiceText is " + custServiceText);
      Assert.assertTrue(custServiceText.contains("CUSTOMER SERVICE"));
    }


   // @Test
    public void favoriteInOnePlace(){
        WebElement favoriteOnePlace = driver.findElement(By.cssSelector("#textinfodiv > h2"));
        Assert.assertTrue(favoriteOnePlace.getText().contains("favorite restaurants"));
    }

   // @Test
    public void andManyMore(){
        WebElement andManyMore = driver.findElement(By.cssSelector("#textinfodiv > h4"));
        Assert.assertTrue(andManyMore.getText().contains("many more"));
    }
    //@Test
    public void aboutFootersInfo(){
        List<WebElement> footers = driver.findElements(By.xpath("//*[@id=\"footer\"]/div/div/h3"));
        for(WebElement e: footers){
            System.out.println("The footer is "+e.getText());
        }
    }

   // @Test
    public void informationFooters(){
        List<WebElement> infoFooters = driver.findElements(By.xpath("//*[@id=\"footer\"]/div/div[2]/a/p"));
        for(WebElement e: infoFooters){
            System.out.println("The information footer is "+e.getText());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        super.tearDown();
    }

    }


