package com.ecvictor.selenium.junit.page_object.testcase;

import com.ecvictor.selenium.junit.page_object.modules.HomePageAction;
import com.ecvictor.selenium.junit.page_object.page_object.AdmissionSubMenuPage;
import com.ecvictor.selenium.junit.page_object.page_object.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;

public class AdmissionPageTest extends BaseTest {


    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testGraduateTest() throws Exception {

        int counter = 3;
        Boolean success = false;
        while (counter-- > 0) {
            System.out.println("Test Start: ");
            HomePageAction homePageAction = new HomePageAction(driver);
            Actions hoverAdmissionButton = homePageAction.hoverAdmissionButton(driver);

            AdmissionSubMenuPage admissionSubMenuPage = new AdmissionSubMenuPage();
            success = admissionSubMenuPage.clickadmissionSubMenu(hoverAdmissionButton, driver);

            if (success) break;
        }
        if (!success)
            Assert.fail("Test failed");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
