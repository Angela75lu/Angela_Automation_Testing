package com.ecvictor.selenium.junit.page_object.modules;

import com.ecvictor.selenium.junit.page_object.page_object.HomePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePageAction extends ActionConfig {
    private WebDriver driver;

    public HomePageAction(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement aboutButton() {
        return driver.findElement(By.xpath(HomePage.aboutButton));
    }


    public WebElement admissionButton() {
        return driver.findElement(By.xpath("//a[@href=\"/admissions.html\" and @class]"));
    }

    public void clickAboutButton() throws InterruptedException {
        this.aboutButton().click();

        int timeout = TIME_OUT;
        while (timeout > 0) {
            String name = driver.getTitle();
            if (name.equals("About")) {
                System.out.println("Found the Graduate admissions: " + timeout);
                break;
            } else {
                System.out.println("Not found the Graduate admissions,wait 500 ms");
                Thread.sleep(INTERVAL);
                timeout -= INTERVAL;
            }
        }
        if (timeout <= 0)
            Assert.fail("Test failed");
    }

    public Actions hoverAdmissionButton(WebDriver driver) throws InterruptedException {
        WebElement menu = this.admissionButton();
        Actions actions = new Actions(this.driver);
        actions.moveToElement(menu).perform();
        return actions;
    }
}
