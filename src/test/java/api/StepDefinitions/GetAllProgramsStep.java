package api.StepDefinitions;

import api.Request.GetAllProgramRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetAllProgramsStep {

    GetAllProgramRequest allProgramRequest = new GetAllProgramRequest();
    public Response response;

    @Given("Admin creates GET Request for the LMS API")
    public void admin_creates_get_request_for_the_lms_api() {
        allProgramRequest.buildRequest();
    }

    @When("Admin sends {string} HTTPS Request with endpoint {string}")
    public void admin_calls_post_https_method_with_valid_endpoint(String method, String endPoint) {
        response = allProgramRequest.sendRequest(method, endPoint);
    }

    @Then("Admin receives {int} {string} Status.")
    public void adminReceivesStatus(int code, String status) {
        response.then().statusCode(code);
    }

    @Given("Admin creates POST Request for the LMS API")
    public void adminCreatesPOSTRequestForTheLMSAPI() {
        allProgramRequest.buildRequest();
    }

    @Given("Admin creates GET Request without Authorization")
    public void adminCreatesGETRequestWithoutAuthorization() {
        allProgramRequest.buildNoAuthRequest();
    }

}
