package com.ecvictor.selenium.junit.page_object.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AboutPage {

    public void clickStudyHereButton(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href=\"/future-students.html\" and @class]")).click();
        Thread.sleep(2000);
        assertEquals("Admissions", driver.getTitle());
    }
}
