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
		features = {"src/test/resources/features"
//				"src/test/resources/features/01userLogin",
//			"src/test/resources/features/02programModule",
//				"src/test/resources/features/03batchModule",
//				"src/test/resources/features/04classModule",
//				"src/test/resources/features/05deleteRequests",
//				"src/test/resources/features/06userLogout"
				}, //location of feature files
		//features = {"src/test/resources/features/02programModule/programCreate.feature" }, //location of feature files
		//tags = "@TC9",
		glue={"api.StepDefinitions"}
		
		)


public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }

}
