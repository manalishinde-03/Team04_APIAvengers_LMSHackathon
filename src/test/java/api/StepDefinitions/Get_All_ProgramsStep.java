package api.StepDefinitions;

import api.Request.Get_All_ProgramRequest;
import api.Request.UserLoginRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class Get_All_ProgramsStep {

    Get_All_ProgramRequest allProgramRequest = new Get_All_ProgramRequest();
    public Response response;

    @Given("Admin creates GET Request for the LMS API")
    public void admin_creates_get_request_for_the_lms_api() {
        allProgramRequest.buildRequest();

    }
    @When("Admin sends HTTPS Request with endpoint")
    public void admin_sends_https_request_with_endpoint() {
        response = allProgramRequest.sendRequest();
    }
    @Then("Admin receives {int} OK Status with response body.")
    public void admin_receives_ok_status_with_response_body(int statusCode) {
        response.then().statusCode(statusCode);

    }
    @Then("Admin receives {int} not found  Status with error message")
    public void admin_receives_not_found_status_with_error_message(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @When("Admin sends HTTPS Request with invalidendpoint")
    public void adminSendsHTTPSRequestWithInvalidendpoint() {
        response = allProgramRequest.sendinvalidRequest();

    }

    @Given("Admin creates POST Request for the LMS API")
    public void adminCreatesPOSTRequestForTheLMSAPI() {
        allProgramRequest.buildRequest();
    }
    @When("Admin sends POST Request with endpoint")
    public void adminSendsPOSTRequestWithEndpoint() {
        response = allProgramRequest.sendinvalidMethod();
    }
    @Then("Admin receives {int} Method Not Allowed")
    public void adminReceivesMethodNotAllowed(int statusCode) {
        response.then().statusCode(statusCode);

    }
    @Given("Admin creates GET Request without Authorization")
    public void adminCreatesGETRequestWithoutAuthorization() {
        allProgramRequest.buildNoAuthRequest();
    }
    @Then("Admin receives {int} Status with response body as Unauthorized")
    public void adminReceivesStatusWithResponseBodyAsUnauthorized(int statusCode) {
        response.then().statusCode(statusCode);
    }


}
