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
	
	// Background	
	
	@Given("Admin sets {string} Authorization to Bearer Token")
	public void admin_sets_authorization_to_bearer_token(String TestCase) {
		//createBatchReq.Background_Auth(TestCase);
	}
	
	
	// Create Batch
	
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
	
	// Get All batches
	
	

	@Given("Admin creates GET Request for the {string}")
	public void admin_creates_get_request_for_the(String GetTestCases) throws IOException {
		createBatchReq.buildGetallBatchrequest(GetTestCases);
	}

	@When("Admin sends the GetAll HTTPS Request with endpoint")
	public void admin_sends_the_get_all_https_request_with_endpoint() {
		createBatchReq.getAllbatchSendrequest();
	}

	@Then("Admin receives status code with response body {string}")
	public void admin_receives_status_code_with_response_body(String string) {
	    
	}
	
	
	
	
	
	
	
	
}
