package com.ecvictor.selenium.cucumber.my_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AdmissionSubMenu extends BaseClass {
    public AdmissionSubMenu(WebDriver driver){
        super(driver);
    }

    @FindBy(how= How.LINK_TEXT, using="Graduate")
    public static WebElement graduateLink;
}
