package com.ecvictor.selenium.junit.page_object.testcase;

import com.ecvictor.selenium.junit.page_object.modules.HomePageAction;
import com.ecvictor.selenium.junit.page_object.page_object.AboutPage;
import com.ecvictor.selenium.junit.page_object.page_object.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AboutPageTest extends BaseTest {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testAbout() throws Exception {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickAboutButton();
    }
    @Test
    public void testValidateStudyHereinAboutPage() throws Exception {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickAboutButton();

        AboutPage aboutPage = new AboutPage();
        aboutPage.clickStudyHereButton(driver);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
