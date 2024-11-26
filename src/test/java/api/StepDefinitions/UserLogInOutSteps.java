package api.StepDefinitions;

import api.Pojo.LogInOutRequestPojo;
import api.Request.UserLogInOutRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import static api.StepDefinitions.ProgramCreateSteps.excelFilePath;

@Log4j2
public class UserLogInOutSteps {

	 UserLogInOutRequest adminLoginReq = new UserLogInOutRequest();
	    public Response response;

	    @Given("Admin creates POST login request body for {string}")
	    public void adminCreatesPOSTLoginRequestBodyFor(String testCaseId) throws Exception {
	        adminLoginReq.loadTestData(excelFilePath, "LogIn-LogOut");
	        LogInOutRequestPojo loginRequestData = adminLoginReq.getLoginRequestData(testCaseId);
	        adminLoginReq.buildRequest(loginRequestData);
	    }

	    @When("Admin sends POST request to login for {string}")
	    public void adminSendsPOSTRequestToLoginFor(String testCaseId) {
	        response = adminLoginReq.sendPostRequest(adminLoginReq.getLoginRequestData(testCaseId));
	    }

	    @Given("Admin creates GET logout request body for {string}")
	    public void adminCreatesGETLogoutRequestBodyFor(String testCaseId) throws Exception {
	        adminLoginReq.loadTestData(excelFilePath, "LogIn-LogOut");
	        LogInOutRequestPojo loginRequestData = adminLoginReq.getLoginRequestData(testCaseId);
	        adminLoginReq.buildRequest(loginRequestData);
	    }

	    @When("Admin sends POST request to logout for {string}")
	    public void adminSendsPOSTRequestToLogoutFor(String testCaseId) {
	        response = adminLoginReq.sendGetRequest(adminLoginReq.getLoginRequestData(testCaseId));
	    }

	    @Then("Admin receives {string} {string} Status.")
	    public void adminReceivesStatus(String code, String status) {
	      //  log.info("Response body: {}", response.body());

	        adminLoginReq.response.then().assertThat().statusCode(Integer.parseInt(code));
	    }
	}
