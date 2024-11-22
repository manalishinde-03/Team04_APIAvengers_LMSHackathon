package api.StepDefinitions;

import api.Request.UserLoginRequest;
import api.Request.UserLogoutRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserLogoutStep {
    UserLogoutRequest adminLogoutReq = new UserLogoutRequest();
    public Response response;

    @Given("Admin creates request")
    public void admin_creates_request() {
        adminLogoutReq.buildRequest();
    }
    @When("Admin calls Get Https method with valid endpoint")
    public void admin_calls_get_https_method_with_valid_endpoint() {
       response = adminLogoutReq.sendRequest();
    }

    @Then("Admin receives {int} ok  and response with Logout successful")
    public void adminReceivesOkAndResponseWithLogoutSuccessful(int StatusCode) {
        response.then().statusCode(StatusCode);
    }


    @When("Admin calls Get Https method with invalid-endpoint")
    public void adminCallsGetHttpsMethodWithInvalidEndpoint() {
        response = adminLogoutReq.sendinvalidRequest();
    }

    @Then("Admin receives {int} Not found")
    public void adminReceivesNotFound(int StatusCode) {
        response.then().statusCode(StatusCode);
    }

    @Given("Admin creates request without Auth")
    public void adminCreatesRequestWithoutAuth() {
        adminLogoutReq.buildNoAuthRequest();
    }

    @Then("Admin receives {int}  unauthorized")
    public void adminReceivesUnauthorized(int StatusCode) {
        response.then().statusCode(StatusCode);
    }
}
