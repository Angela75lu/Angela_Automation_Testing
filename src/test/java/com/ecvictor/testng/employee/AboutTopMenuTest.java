package com.ecvictor.testng.employee;


import com.ecvictor.testng.employee.Concordia.HomePageMainMenuAction;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AboutTopMenuTest extends BaseTestSetUp {
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testAbout() throws Exception {
        int counter = 3;
        boolean success = false;
        HomePageMainMenuAction homePageAction = new HomePageMainMenuAction(driver);
        while (counter-- > 0) {
            System.out.println("About Button Test Starts....");
            success = homePageAction.aboutButtonTest();
            if (success) break;
        }
        if (!success)
            Assert.fail("about button testing Failed");
    }
    @Test
    public void testStudyHere() throws Exception {
        int counter = 3;
        boolean success = false;
        HomePageMainMenuAction homePageAction = new HomePageMainMenuAction(driver);
        while (counter-- > 0) {
            System.out.println("Study Here Button Testing Starts....");
            success = homePageAction.studyHereButtonTest(driver);
            if (success) break;
        }
        if (!success)
            Assert.fail("study here button testing Failed");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }


}
