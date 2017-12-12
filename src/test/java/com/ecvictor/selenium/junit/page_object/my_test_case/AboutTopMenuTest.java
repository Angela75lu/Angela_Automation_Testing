package com.ecvictor.selenium.junit.page_object.my_test_case;

import com.ecvictor.selenium.junit.page_object.my_page_modules.HomePageMainMenuAction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

public class AboutTopMenuTest extends BaseTestSetUp {
    @Before
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

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }


}
