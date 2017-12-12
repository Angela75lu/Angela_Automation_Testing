package com.ecvictor.testng.employee.Concordia;

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