package api.TestRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty","html:target/cucumber.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
				},
		monochrome=false,  //console output color
		features = {"src/test/resources/features/02programModule/programCreate.feature" }, //location of feature files
		tags = "@TC4",
		glue={"api.StepDefinitions"}
		
		)

public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
    }

}
