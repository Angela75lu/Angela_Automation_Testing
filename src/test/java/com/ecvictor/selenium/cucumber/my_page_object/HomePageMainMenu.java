package com.ecvictor.selenium.cucumber.my_page_object;

import com.ecvictor.selenium.cucumber.pageobjects.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageMainMenu extends BaseClass {
    public HomePageMainMenu(WebDriver driver){
        super(driver);
    }

    @FindBy(how= How.XPATH, using="//a[@href=\"/about.html\" and @class]")
    public static WebElement aboutMenu;

    @FindBy(how= How.XPATH, using="//a[@href=\"/academics.html\" and @class]")
    public static WebElement academics;

    @FindBy(how= How.XPATH, using="//a[@href=\"/admissions.html\" and @class]")
    public static WebElement admissionsMenu;

    //@FindBy(how= How.XPATH, using="//*[@id='content-main']//*[@href=\"/admissions.html\"]")
    @FindBy(how=How.XPATH, using="//*[@id='content-main']/div/div/div[2]/div[4]/div[1]/div[1]/div/div[1]/div/div/a")
    public static WebElement studyHere;
}

