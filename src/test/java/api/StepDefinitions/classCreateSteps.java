package api.StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.Request.createClassRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class classCreateSteps {

	createClassRequest createClassReq = new createClassRequest();
	public Response response;

	// Scenario: Check if admin is able to create class with all positive and negative scenarios

	@Given("Admin creates Post Request for the LMS with below {string} for classCreation")
	public void admin_creates_post_request_for_the_lms_with_below_for_classCreation(String TestCase)
			throws JsonProcessingException, IOException {
		createClassReq.createClassbuildRequest(TestCase);
	}

	@When("Admin calls POST request with endpoint for classCreation")
	public void admin_calls_post_request_with_endpoint_for_classCreation() {
		createClassReq.CreateClassSendRequest();
	}

	@Then("Admin receive {string} created status for classCreation")
	public void admin_receive_created_status_for_classCreation(String Exp_StatusCode) {
		// int expStatusCode = Integer.parseInt(Exp_StatusCode);
		createClassReq.StatuscodeValidation(Exp_StatusCode);

		if (Integer.parseInt(Exp_StatusCode) == 201) {
			createClassReq.SchemaValidation();
		}
	}

}