package com.ecvictor.testng.pinpineat.test_case;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import com.ecvictor.testng.pinpineat.Page_Module.SignUpAction;
import com.ecvictor.testng.pinpineat.page_object.HomePageMainMenu;
import com.ecvictor.testng.pinpineat.page_object.Register_Form;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpTest extends PinPinUtil {

    private SignUpAction action;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    @DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src/test/Resources/signUp.xlsx",
                "SignUp");
        return(retObjArr);
    }

    /*@Test(dataProvider = "DP1")
    public void testSignUp(String userName,
                                        String email, String pass, String rePass) throws Exception {
        clickWhenReady(HomePageMainMenu.loginLink, 30, driver);
        clickWhenReady(Register_Form.signUpLink,30, driver);
       System.out.println("In signUp testing....");



        //System.out.println("username is "+userName);
       //System.out.println("email is "+email);
       //System.out.println("pass is "+pass);
       //System.out.println("rePass is "+rePass);
    }*/

    @Test(dataProvider = "DP1")
    public void testSignUp(String userName, String email, String pwd, String rePwd) throws Exception {
        action = new SignUpAction(driver);
        clickWhenReady(HomePageMainMenu.loginLink, 30, driver);
        clickWhenReady(Register_Form.signUpLink,30, driver);
        action.populateRegisterForm(userName, email, pwd, rePwd);
        if(userName==null || email==null||pwd==null ||rePwd==null || (email !=null && !action.checkValidEmail(email))){
            //*[@id="loginDiv"]/div[3]/div/div/form/div[5]/button
            String disable = action.signUpBtn().getAttribute("disabled");
            Assert.assertEquals("true", disable);
        } else if(!(pwd.equals(rePwd))){
            action.signUpBtn().click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals("passwords are not identical.", alert.getText());
            alert.accept();
           }
           else {
            action.signUpBtn().click();
            WebElement confirmInfo = driver.findElement(By.xpath("//*[@id=\"signupalert\"]/p"));
            System.out.println("info is "+confirmInfo.getText());
            Assert.assertEquals("Update 'bb@cc.com'fail! Cause: duplicate email", confirmInfo.getText());
        }
    }



    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
