package com.ecvictor.selenium.cucumber.my_modules;

import com.ecvictor.selenium.cucumber.helpers.Log;
import com.ecvictor.selenium.cucumber.my_page_object.AdmissionSubMenu;
import com.ecvictor.selenium.cucumber.my_page_object.HomePageMainMenu;
import com.ecvictor.selenium.cucumber.pageobjects.AutomationHomePage;
import com.ecvictor.selenium.cucumber.pageobjects.LoginPage;
import com.ecvictor.selenium.junit.page_object.my_page_modules.HomePageMainMenuAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HomePageAction {

    public static void clickWhenReady(String link, int timeout,WebDriver driver) {

        WebElement element = null;

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(link)));

        element.click();

    }

    public static void AboutClick(WebDriver driver) throws Exception {
       //Methods 1 using the explicit wait
        String aboutLink = "//a[@href=\"/about.html\" and @class]";
        clickWhenReady(aboutLink, 30, driver);

       //Methods 2 using the ThreadSleep(interval) within TIMEOUT
     //   HomePageMainMenu.aboutMenu.click();
      //  validateLink(driver, "About");
     //   Log.info("Click action is perfromed on About link");
    }

    public static void studyHereButtonClick(WebDriver driver) throws InterruptedException {
      /*int counter = 3;
      boolean success=false;
        while (counter-- > 0) {
            System.out.println("Study Here Button Testing Starts....");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,250)", "");
            HomePageMainMenu.studyHere.click();
            success = validateLink(driver, "Admissions");
            if (success) break;
        }
        if (!success)
            System.out.println("study here button testing Failed");*/

       //String studyHere = "//*[@id='content-main']/div/div/div[2]/div[4]/div[1]/div[1]/div/div[1]/div/div/a";
        String studyHere ="//*[@id='content-main']//*[@href=\"/admissions.html\"]";
        clickWhenReady(studyHere, 30, driver);

    }

    public static void hoverAdmissionButton(WebDriver driver, Actions actions) throws InterruptedException {
        actions.moveToElement(HomePageMainMenu.admissionsMenu).perform();
    }

    public static void clickGraduateSubMenu(WebDriver driver, String str) throws InterruptedException {
        Actions actions = new Actions(driver);
       hoverAdmissionButton(driver, actions);
       actions.moveToElement(HomePageMainMenu.admissionsMenu);
       actions.perform();
       actions.moveToElement(AdmissionSubMenu.graduateLink);
       actions.click().build().perform();
    }

    public static boolean validateLink(WebDriver driver, String str)  {
        int timeout = 3000;
        int interval = 500;
        boolean success = false;
        while (timeout > 0) {
            String name = driver.getTitle();
            if (name.equals(str)) {
                System.out.println(str + " link found." + timeout);
                return true;
            } else {
                System.out.println("Not found " + str + " wait 500 ms");
                try {
                    Thread.sleep(interval);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeout -= interval;
            }
        }
        if (timeout <= 0)
            Assert.fail("Test failed");
        return false;
    }
}
