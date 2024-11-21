package api.StepDefinitions;

import api.Request.CreateProgramRequest;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ProgramCreateSteps {
	
	CreateProgramRequest createProgramRequest = new CreateProgramRequest();
	public Response response;

	@Given("Admin creates POST Request for the LMS with request body")
	public void admin_creates_post_request_for_the_lms_with_request_body() {
		
		createProgramRequest.buildRequest();
	}
	
	@Given("Admin creates POST Request with only mandatory fields")
	public void admin_creates_post_request_with_mandatoryFields() {
		createProgramRequest.buildRequestWithMandatoryDetails();
	}

	@When("Admin sends POST HTTPS Request and request Body with endpoint")
	public void admin_sends_post_https_request_and_request_body_with_endpoint() {
		response = createProgramRequest.sendRequest();
	}


	@When("Admin sends HTTPS Request and  request Body with only mandatory fields")
	public void admin_sends_https_request_and_request_body_with_only_mandatory_fields() {
		
		response = createProgramRequest.sendRequest();
	}


	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
		System.out.println("Program ID :" +CommonUtils.getProgramID());
	}
	
	@Given("Admin creates POST Request with No Authorization")
	public void admin_creates_post_request_with_NoAuth() {
		
		createProgramRequest.buildRequest("NoAuth");
	}
	
	@When("Admin sends POST HTTPS Request without Authorization token")
	public void admin_sends_https_request_withNoAuth() {
		
		response = createProgramRequest.sendRequestWithNoAuth();
	}


	@Then("Admin receives {int} Unauthorized Status code")
	public void admin_receives_unauthorized_status(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}
}
