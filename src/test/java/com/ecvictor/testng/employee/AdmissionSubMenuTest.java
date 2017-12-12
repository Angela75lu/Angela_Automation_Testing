package com.ecvictor.testng.employee;


import com.ecvictor.testng.employee.Concordia.AdmissionSubMenu;
import com.ecvictor.testng.employee.Concordia.AdmissionSubMenuAction;
import com.ecvictor.testng.employee.Concordia.HomePageMainMenuAction;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdmissionSubMenuTest extends BaseTestSetUp {
    @BeforeMethod
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
            success = gradatePageAction.clickGraduateSubMenu(action,driver, AdmissionSubMenu.graduate);
            if(success) break;
        }
        if (!success)
            Assert.fail("Test failed");
    }
    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
