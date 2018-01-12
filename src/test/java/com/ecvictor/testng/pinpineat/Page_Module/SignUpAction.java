package com.ecvictor.testng.pinpineat.Page_Module;

import com.ecvictor.testng.pinpineat.page_object.Register_Form;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SignUpAction {
    private WebDriver driver;
    public SignUpAction(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement signUpLink() {
        return driver.findElement(By.xpath(Register_Form.signUpLink));
    }
    public WebElement userName() {
        return driver.findElement(By.id(Register_Form.userName));
    }

    public WebElement email() {
        return driver.findElement(By.id(Register_Form.email));
    }

    public WebElement pwd(){
        return driver.findElement(By.id(Register_Form.pass));
    }
    public WebElement rePassword(){
        return driver.findElement(By.id(Register_Form.rePassword));
    }

    public WebElement signUpBtn() {
        return driver.findElement(By.xpath(Register_Form.signUpBtn));

    }

    public void populateRegisterForm(String userName, String email, String pwd, String rePwd){
        userName().clear();
        email().clear();
        pwd().clear();
        rePassword().clear();
        if(userName != null)
            userName().sendKeys(userName);
        if(email != null)
            email().sendKeys(email);
        if(pwd != null)
            pwd().sendKeys(pwd);
        if(rePwd != null)
            rePassword().sendKeys(rePwd);
    }

    public boolean checkValidEmail(String email){
        if(email.indexOf('@')>=0)
            return true;
            return false;
    }

    public boolean comparePassword(String pass, String rePass){
        if(pass.equals(rePass))
            return true;
        return false;
    }

}

