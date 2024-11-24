package api.StepDefinitions;

import api.Pojo.LogInOutRequestPojo;
import api.Request.UserLogInOutRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static api.StepDefinitions.ProgramCreateSteps.EXCEL_PATH;

public class UserLogInOutSteps {

    UserLogInOutRequest adminLoginReq = new UserLogInOutRequest();
    public Response response;

    @Given("Admin creates POST login request body for {string}")
    public void adminCreatesPOSTLoginRequestBodyFor(String testCaseId) throws Exception {
        adminLoginReq.loadTestData(EXCEL_PATH, "LogIn-LogOut");
        LogInOutRequestPojo loginRequestData = adminLoginReq.getLoginRequestData(testCaseId);
        adminLoginReq.buildRequest(loginRequestData);
    }

    @When("Admin sends POST request to login for {string}")
    public void adminSendsPOSTRequestToLoginFor(String testCaseId) {
        response = adminLoginReq.sendPostRequest(adminLoginReq.getLoginRequestData(testCaseId));
    }

    @Given("Admin creates GET logout request body for {string}")
    public void adminCreatesGETLogoutRequestBodyFor(String testCaseId) throws Exception {
        adminLoginReq.loadTestData(EXCEL_PATH, "LogIn-LogOut");
        LogInOutRequestPojo loginRequestData = adminLoginReq.getLoginRequestData(testCaseId);
        adminLoginReq.buildRequest(loginRequestData);
    }

    @When("Admin sends POST request to logout for {string}")
    public void adminSendsPOSTRequestToLogoutFor(String testCaseId) {
        response = adminLoginReq.sendGetRequest(adminLoginReq.getLoginRequestData(testCaseId));
    }

    @Then("Admin receives {string} {string} Status.")
    public void adminReceivesStatus(String code, String status) {
        adminLoginReq.response.then().assertThat().statusCode(Integer.parseInt(code));
    }
}
