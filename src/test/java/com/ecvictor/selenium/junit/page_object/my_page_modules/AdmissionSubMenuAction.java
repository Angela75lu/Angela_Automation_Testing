package com.ecvictor.selenium.junit.page_object.my_page_modules;

import com.ecvictor.selenium.junit.page_object.my_page_object.AdmissionSubMenu;
import com.ecvictor.selenium.junit.page_object.my_page_object.HomePageMainMenu;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AdmissionSubMenuAction extends TestConfig {
    private WebDriver driver;

    public AdmissionSubMenuAction(WebDriver driver) {
        this.driver = driver;
    }

    public boolean clickGraduateSubMenu(Actions actions, WebDriver driver, String str) throws InterruptedException {
        HomePageMainMenuAction mainMenuAction = new HomePageMainMenuAction(driver);
        WebElement graduatesButton = mainMenuAction.graduatesButton(driver);
        actions.moveToElement(graduatesButton);
        actions.click().build().perform();
        return mainMenuAction.validateLink(str);
    }

}