package com.ecvictor.selenium.cucumber.step_definitions;

import com.ecvictor.selenium.cucumber.my_modules.HomePageAction;
import com.ecvictor.selenium.cucumber.my_page_object.AdmissionSubMenu;
import com.ecvictor.selenium.cucumber.my_page_object.HomePageMainMenu;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConcordiaStepDefs {
    public WebDriver driver ;

    public ConcordiaStepDefs() {
        driver = Hooks.driver;
    }

    @Given("^I open concordia website$")
    public void i_open_seleniumframework_website() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://www.concordia.ca");
    }
    @When("^I click the about menu$")
    public void I_click_about_menu() throws Throwable {
        PageFactory.initElements(driver, HomePageMainMenu.class);
        HomePageAction.AboutClick(driver);
    }

    @Then("^the about page come out$")
    public void the_about_URL() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("^I click the study here link")
    public void I_click_study_here_website() throws Throwable {
       HomePageAction.studyHereButtonClick(driver);
    }

    @Then("^the admission page is showed$")
    public void i_print_title_and_URL() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        HomePageAction.validateLink(driver, "Admissions");
    }

    @When ("^I move over the admission link$")
    public void i_move_over_admission_link() throws Throwable{
        PageFactory.initElements(driver, HomePageMainMenu.class);
    }
    @And("^I click the graduate link$")
    public void I_click_graduate_menu() throws Throwable {
        // pageFactory.initElement() is very important to be called here
        PageFactory.initElements(driver, AdmissionSubMenu.class);
        HomePageAction.clickGraduateSubMenu( driver, "Graduate");
    }
    @Then("^the graduate page is showed$")
    public void graduatePage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        HomePageAction.validateLink(driver, "Graduate");
    }
}
