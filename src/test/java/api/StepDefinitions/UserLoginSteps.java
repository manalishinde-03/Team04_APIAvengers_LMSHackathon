package api.StepDefinitions;

import api.Request.UserLoginRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserLoginSteps {
	
UserLoginRequest adminLoginReq = new UserLoginRequest();
public Response response;
	
@Given("Admin creates request with valid credentials")
public void admin_creates_request_with_valid_credentials() {
	
	adminLoginReq.buildRequest();
    
}

@When("Admin calls Post Https method  with valid endpoint")
public void admin_calls_post_https_method_with_valid_endpoint() {

	response = adminLoginReq.sendRequest();
}

@Then("Admin receives {int} OK with auto generated token")
public void admin_receives_created_with_auto_generated_token(int statusCode) {
	
	response.then().statusCode(statusCode);
}

}
