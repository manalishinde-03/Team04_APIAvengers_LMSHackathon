package api.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.Request.CreateBatchRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CreateBatchSteps {
	
	CreateBatchRequest createBatchReq= new CreateBatchRequest();
	public Response response;	
	
	@Given("Admin creates POST request body for the Testcases {string}")
	public void admin_creates_post_request_body_for_the_testcases(String TestCase) throws JsonProcessingException, IOException {
		createBatchReq.createBatchbuildRequest(TestCase);
	}
	
	
	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		createBatchReq.CreateBatchSendRequest();
	}

	
	@Then("Admin receives respective status codes {string} for the TestCases")
	public void admin_receives_respective_status_codes_for_the_test_cases(String Exp_StatusCode) {
		createBatchReq.StatuscodeValidation(Exp_StatusCode);
		
		if(Integer.parseInt(Exp_StatusCode) ==201)
		{
			createBatchReq.SchemaValidation();
		}
		
		
	}
	
}
