package api.StepDefinitions;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;

import api.Request.DeleteRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteSteps
{

    DeleteRequest delRequest = new DeleteRequest();
    private RequestSpecification request;
    private Response response;
    private String ClassId = "108";


    @Given("Admin creates DELETE Request")
    public void admin_creates_delete_request() throws IOException
    {
        RestAssured.baseURI = CommonUtils.baseURI;
    }

    @When("Admin sends HTTPS Request with endpoint for Delete Class by Class Id")
    public void admin_sends_https_request_with_endpoint_for_delete_class_by_class_id()
    {
        String classid = "/deleteByClass/" + ClassId + "";
        response = delRequest.buildRequest(classid, "delete", "Bearer");

    }

    @Then("Admin receives {int} Status OK with response body for Delete Class by Class Id")
    public void admin_receives_status_ok_with_response_body_for_delete_class_by_class_id(Integer expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }


    @When("Admin sends HTTPS Request with invalid endpoint for Delete Class by Class Id")
    public void admin_sends_https_request_with_invalid_endpoint_for_delete_class_by_class_id() {

        response = delRequest.buildRequest("/deleteByClassabc/", "delete", "Bearer");

    }

    @Then("Admin receives {int} Not Found Status with error message for Delete Class by Class Id")
    public void admin_receives_not_found_status_with_error_message_for_delete_class_by_class_id(Integer expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

    }
    @When("Admin sends HTTPS Request with invalid Class Id for Delete Class by Class Id")
    public void admin_sends_https_request_with_invalid_class_id_for_delete_class_by_class_id() {

        response = delRequest.buildRequest("/deleteByClass/10007", "delete", "Bearer");

    }

    @Then("Admin receives {int} Not Found Status with message visible for Delete Class by Class Id")
    public void admin_receives_not_found_status_with_message_visible_for_delete_class_by_class_id(
            Integer expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

    }
    @When("Admin sends HTTPS Request without authorization for Delete Class by Class Id")
    public void admin_sends_https_request_without_authorization_for_delete_class_by_class_id() {
        String classid = "/deleteByClass/" + ClassId + "";
        response = delRequest.buildRequest(classid, "delete", "noauth");
    }

    @Then("Admin receives {int} Unauthorized Status for Delete Class by Class Id")
    public void admin_receives_unauthorized_status_for_delete_class_by_class_id(
            Integer expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

    }



}
