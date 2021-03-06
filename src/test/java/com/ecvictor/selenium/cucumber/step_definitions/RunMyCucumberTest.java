package com.ecvictor.selenium.cucumber.step_definitions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "html:target/cucumber-html-report",
                "json:target/cucumber.json"},
		//tags={"@ConcordiaAbout"}
		//tags = {"@ConcordiaStudyHere"}
		tags ={"@ConcordiaAbout,@ConcordiaStudyHere, @GraduateUnderAdmission"}
)

public class RunMyCucumberTest {
}
