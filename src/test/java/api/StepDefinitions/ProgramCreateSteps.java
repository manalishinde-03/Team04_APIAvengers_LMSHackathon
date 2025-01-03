package api.StepDefinitions;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.Pojo.CreateProgramRequestPojo;
import api.Request.CreateProgramRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProgramCreateSteps extends CommonUtils {

	CreateProgramRequest createProgramRequest = new CreateProgramRequest();
	CreateProgramRequestPojo createProgramPojo = new CreateProgramRequestPojo();
	public Response response;
	String DataRequestBody;
	ObjectMapper objectMapper = new ObjectMapper();

	private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

	public static final String EXCEL_PATH = CommonUtils.excelFilePath;
	private static final String SHEET_NAME = "Program";

	private static final Logger log = LogManager.getLogger(ProgramCreateSteps.class);

	@Given("Admin creates POST request body for {string}")
	public void admin_creates_post_request_body_for(String testCaseID) throws Exception {
		// Load test data
		createProgramRequest.loadTestData(EXCEL_PATH, SHEET_NAME);

		// Verify requested TestCaseID exists
		CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);

		log.info("Loaded Test Data for TestCaseID " + testCaseID + ": " + programData.getProgramName() + ", "
				+ programData.getProgramDescription());
	}

	@When("Admin sends POST request to create a program for {string}")
	public void admin_sends_post_request_to_create_a_program(String testCaseID) throws Exception {

		response = createProgramRequest.sendPostRequest(testCaseID);

	}

	@When("Admin sends POST request with No Auth to create a program for {string}")
	public void admin_sends_post_request_NoAuth_create_program(String testCaseID) throws Exception {

		Map<String, String> headers = CommonUtils.getHeadersWithoutAuth();
		CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);

		String endpoint = programData.getEndPoint();

		response = createProgramRequest.sendPostRequestGeneric(endpoint, programData, headers, 401);

	}

	@When("Admin sends POST request to an invalid endpoint for {string}")
	public void admin_sends_post_request_invalidEndpt_create_program(String testCaseID) throws Exception {

		Map<String, String> headers = CommonUtils.getDefaultHeaders();
		CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);

		String invalidEndpoint = programData.getEndPoint();

		response = createProgramRequest.sendPostRequestGeneric(invalidEndpoint, programData, headers, 404);

	}

	@When("Admin sends POST request for creating program with existing programName {string}")
	public void admin_sends_post_request_existingProgName_create_program(String testCaseID) throws Exception {

		Map<String, String> headers = CommonUtils.getDefaultHeaders();
		CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);

		String endpoint = programData.getEndPoint();
		System.out.println("Invalid Endpoint :" + endpoint);

		response = createProgramRequest.sendPostRequestGeneric(endpoint, programData, headers, 400);
	}

	@When("Admin sends {string} request to create program for {string}")
	public void admin_sends_get_request_invalidMethod_create_program(String httpReq, String testCaseID)
			throws Exception {

		response = createProgramRequest.sendhttpMethod(httpReq, testCaseID);

	}

	@When("Admin sends POST request with invalid request body for {string}")
	public void admin_sends_get_request_invalidReqBody_create_program(String testCaseID) throws Exception {

		Map<String, String> headers = CommonUtils.getDefaultHeaders();
		CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);

		String endpoint = programData.getEndPoint();
		System.out.println("Invalid Endpoint :" + endpoint);
		response = createProgramRequest.sendPostRequestGeneric(endpoint, programData, headers, 400);

	}

	@Given("Admin creates PUT request body for {string}")
	public void admin_creates_put_request_body_for(String testCaseID) throws Exception {
		createProgramRequest.loadTestData(EXCEL_PATH, SHEET_NAME);
		CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
		createProgramRequest.buildRequest(programData);

	}

	@Given("Admin sets an invalid base URI")
	public void admin_sets_invalid_base_uri() {
		RestAssured.baseURI = "http://invalid-base-uri.com";
		System.out.println("Base URI set to: " + RestAssured.baseURI);
	}

	@When("Admin sends PUT request for {int} with valid request body for {string}")
	public void admin_sends_put_request_to_create_a_program(int progIndex, String testCaseID) throws Exception {

		response = createProgramRequest.sendPUTRequest(progIndex, testCaseID);

	}

	@When("Admin sends PUT request with invalid Program Name for {string}")
	public void admin_sends_put_request_invalidProgramName(String testCaseID) throws Exception {

		response = createProgramRequest.sendPUTRequest(testCaseID);

	}

	@When("Admin sends PUT request with No Auth for {string}")
	public void admin_sends_put_request_NOAuth(String testCaseID) throws Exception {

		response = createProgramRequest.sendPUTRequestNoAuth(testCaseID);

	}

	@When("Admin sends PUT request for {int} by Program ID for {string}")
	public void admin_sends_put_request_byProgId(int progIndex, String testCaseID) throws Exception {

		response = createProgramRequest.sendPUTRequestByID(progIndex, testCaseID);

	}

	@When("Admin sends PUT request for {int} with invalid request body for {string}")
	public void admin_sends_put_request_invalidBaseURI(int progIndex, String testCaseID) throws Exception {

		try {
			response = createProgramRequest.sendPUTRequestByIDInvalidTC(progIndex, testCaseID);

		} catch (Exception e) {
			log.error("Exception during PUT request: " + e.getMessage());
		}

	}

	@When("Admin sends PUT request for {int} with invalid method for {string}")
	public void admin_sends_put_request_invalidMethod(int progIndex, String testCaseID) throws Exception {

		response = createProgramRequest.sendPUTRequestByIDInvalidMethod(progIndex, testCaseID);

	}

	@Then("Response status code should be {string}")
	public void response_status_code_should_be(String expectedStatusCode) {

		if (this.response == null) {
			log.error("Response is null. No response received.");

		} else {
			int actualStatusCode = this.response.getStatusCode();
			log.info("Actual Status Code -" + actualStatusCode);
			Assert.assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode),
					"Expected status code does not match actual status code.");
		}
	}
}
