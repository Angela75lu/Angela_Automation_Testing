package com.ecvictor.testng.employee.Concordia;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePageMainMenuAction extends TestConfig {
    private WebDriver driver;

    public HomePageMainMenuAction(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement aboutButton() {
        return driver.findElement(By.xpath(HomePageMainMenu.aboutMenu));
    }
    public WebElement admissionButton() {
        return driver.findElement(By.xpath(HomePageMainMenu.admissions));
    }

    public WebElement studyHereButton() {
        return driver.findElement(By.xpath(HomePageMainMenu.studyHere));
    }

    public WebElement graduatesButton(WebDriver driver) {
        return driver.findElement(By.linkText(AdmissionSubMenu.graduate));

    }

    public void clickAboutButton(){
        this.aboutButton().click();
    }

    public Actions hoverAdmissionButton(WebDriver driver) throws InterruptedException {
        WebElement menu = this.admissionButton();
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();
        return actions;
    }

    public boolean aboutButtonTest() throws InterruptedException {
        this.clickAboutButton();
        return validateLink("About");
    }
    public boolean studyHereButtonTest(WebDriver driver) throws InterruptedException {
        this.clickAboutButton();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        // Thread.sleep(1000);
        this.studyHereButton().click();
        // Thread.sleep(2000);
        return validateLink("Admissions");
    }

    public boolean validateLink(String str)  {
        int timeout = TIME_OUT;
        boolean success = false;
        while (timeout > 0) {
            String name = driver.getTitle();
            if (name.equals(str)) {
                System.out.println(str + " link found." + timeout);
                return true;
            } else {
                System.out.println("Not found " + str + " wait 500 ms");
                try {
                    Thread.sleep(INTERVAL);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeout -= INTERVAL;
            }
        }

        return false;

    }

}
