package api.StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.Request.createClassRequest;
import api.Request.getClassRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class classGetAllSteps {

	public Response response;
    getClassRequest getClassReq= new getClassRequest();
    
    private static final Logger log = LogManager.getLogger(classGetAllSteps.class);

	@Given("Admin creates GET Request for the {string} for classmodule")
	public void admin_creates_get_request_for_the_for_classmodule(String GetTestCases) throws IOException {
		RestAssured.baseURI = CommonUtils.baseURI;
		getClassReq.buildGetallBatchrequest(GetTestCases);
	}

	@When("Admin sends the GetAll HTTPS Request with endpoint for classmodule")
	public void admin_sends_the_get_all_https_request_with_endpoint_for_classmodule() {
		//getClassReq.buildRequest("getendpoint", "get", "getauthorization");
		getClassReq.getAllClassSendRequest();
	}
	//"/allClasses", "get", "Bearer"

	@Then("Admin receives status code with response body {string} for classmodule")
	public void admin_receives_status_code_with_response_body_for_classmodule(String Exp_StatusCode) {
		getClassReq.StatuscodeValidation(Exp_StatusCode);
	}

}
