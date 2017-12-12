package com.ecvictor.selenium.junit.page_object.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AdmissionSubMenuPage extends PageConfig {

    public WebElement graduatesButton(WebDriver driver) {
        return driver.findElement(By.linkText("Graduate"));
    }

    public boolean clickadmissionSubMenu(Actions actions, WebDriver driver) throws InterruptedException {
        WebElement graduatesButton = this.graduatesButton(driver);
        actions.moveToElement(graduatesButton);
        actions.click().build().perform();
        return validateAdmissionPage(driver);
    }

    private boolean validateAdmissionPage(WebDriver driver) throws InterruptedException {
        //wait for page shown up
        int timeout = TIME_OUT;
        while (timeout > 0) {
            String name = driver.getTitle();
            if (name.equals("Graduate")) {
                System.out.println("Found the Graduate admissions: " + timeout);
                return true;
            } else {
                System.out.println("Not found the Graduate admissions,wait 500 ms");
                Thread.sleep(INTERVAL);
                timeout -= INTERVAL;

            }
        }
        return false;
    }
}
