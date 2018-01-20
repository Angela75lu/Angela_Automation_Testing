package com.ecvictor.testng.pinpineat.Page_Module;

import com.ecvictor.testng.pinpineat.page_object.CheckoutInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutAction {
    private WebDriver driver;
    public CheckoutAction(WebDriver driver){
        this.driver = driver;
    }
    public WebElement streetElem() { return driver.findElement(By.xpath(CheckoutInfo.street));}
    public WebElement cityElem() { return driver.findElement(By.xpath(CheckoutInfo.city));}
    public WebElement postCodeElem(){ return driver.findElement(By.xpath(CheckoutInfo.postalCode));}
    public WebElement fname() {return driver.findElement(By.xpath(CheckoutInfo.firstName));}
    public WebElement lname() {return driver.findElement(By.xpath(CheckoutInfo.lastName));}
    public WebElement phoneNum() {return driver.findElement(By.xpath(CheckoutInfo.phone));}
    public WebElement email(){return driver.findElement(By.xpath(CheckoutInfo.email));}
    public WebElement deliveryNext() {return driver.findElement(By.xpath(CheckoutInfo.deliveryNextBtn));}
    public WebElement userDetailsBtn(){return driver.findElement(By.xpath(CheckoutInfo.userDetailsBtn));}
    public WebElement payBtn() {return driver.findElement(By.xpath(CheckoutInfo.paynowBtn));}
    public WebElement cardHolderElem() {return driver.findElement(By.xpath(CheckoutInfo.cardholderName));}
    public WebElement cardNum() {return driver.findElement(By.xpath(CheckoutInfo.cardNum));}
    public WebElement expireMon() {return driver.findElement(By.xpath(CheckoutInfo.month));}
    public WebElement expireYear() {return driver.findElement(By.xpath(CheckoutInfo.year));}
    public WebElement cvc() {return driver.findElement(By.xpath(CheckoutInfo.cvc));}


    public void populateDelivery(String street, String city, String postCode){
          streetElem().sendKeys(street);
          cityElem().sendKeys(city);
          postCodeElem().sendKeys(postCode);
    }
    public void populateUserDetails(String firstname, String lastname, String phone, String email)
    {
        fname().sendKeys(firstname);
        lname().sendKeys(lastname);
        phoneNum().sendKeys(phone);
        this.email().sendKeys(email);
    }

    public void populatePayment(String cardholder, String cardNum, String mon, String year, String cvc){
        cardHolderElem().sendKeys(cardholder);
        this.cardNum().sendKeys(cardNum);
        expireMon().sendKeys(mon);
        expireYear().sendKeys(year);
        this.cvc().sendKeys(cvc);
        }
    }



