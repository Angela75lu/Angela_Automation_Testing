package com.ecvictor.selenium.junit.page_object.my_test_case;

import com.ecvictor.selenium.junit.page_object.my_page_modules.AdmissionSubMenuAction;
import com.ecvictor.selenium.junit.page_object.my_page_modules.HomePageMainMenuAction;
import com.ecvictor.selenium.junit.page_object.my_page_object.AdmissionSubMenu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;

public class AdmissionSubMenuTest extends BaseTestSetUp {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testAdmissionSubMenu() throws Exception {
        int counter = 3;
        boolean success = false;
        HomePageMainMenuAction admissionMenuAction = new HomePageMainMenuAction(driver);
        while (counter-- > 0) {
            System.out.println("Test Start: ");
            Actions action = admissionMenuAction.hoverAdmissionButton(driver);
            AdmissionSubMenuAction gradatePageAction = new AdmissionSubMenuAction(driver);
            success = gradatePageAction.clickGraduateSubMenu(action,driver,AdmissionSubMenu.graduate);
            if(success) break;
        }
        if (!success)
            Assert.fail("Test failed");
    }
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
