package api.StepDefinitions;

import api.Payload.CreateProgramPayload;
import api.Pojo.CreateProgramRequestPojo;
import api.Request.CreateProgramRequest;
import api.Utility.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

public class ProgramCreateSteps extends CommonUtils {
	
	CreateProgramRequest createProgramRequest = new CreateProgramRequest();
	CreateProgramRequestPojo createProgramPojo = new CreateProgramRequestPojo();
	CreateProgramPayload createProgramPayload = new CreateProgramPayload();
	public Response response;
	//public static String baseURI ;
	String DataRequestBody;
	ObjectMapper objectMapper = new ObjectMapper();
	//ExcelUtil excelRead = new ExcelUtil("src/test/resources/TestData/testData.xlsx");
	
	 private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

	    public static final String EXCEL_PATH = "./src/test/resources/TestData/testData.xlsx";
	    private static final String SHEET_NAME = "Program";
	

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
	
	
	/********************************Data Driven Steps******************************************/
	
	@Given("Admin sets Authorization")
    public void the_base_url() {
		RestAssured.baseURI = CommonUtils.baseURI;
    }
	
	@When("Verifying POST Program request with data from all Excel")
    public void verifying_post_request_with_data_from_all_Excel() {
       createProgramRequest.buildRequestExcel();
		response = createProgramRequest.sendRequestExcel();
		
    }

	 @Then("Response data should match the request from all Excel data")
	    public void the_response_data_should_match_the_request_from_all_Excel_data() {
	      
		 
	    }
	 /********************************Anbu's Data Driven Steps******************************************/
	 

		/*
		 * @Given("Admin creates POST request body for the Testcases {string}") public
		 * void admin_creates_post_request_body_for_the_testcases(String TestCase)
		 * throws JsonProcessingException, IOException {
		 * createProgramRequest.createBatchbuildRequest(TestCase); }
		 * 
		 * 
		 * @When("Admin sends HTTPS Request with endpoint") public void
		 * admin_sends_https_request_with_endpoint() {
		 * createProgramRequest.CreateBatchSendRequest(); }
		 * 
		 * 
		 * @Then("Admin receives respective status codes {string} for the TestCases")
		 * public void admin_receives_respective_status_codes_for_the_test_cases(String
		 * Exp_StatusCode) { createProgramRequest.StatuscodeValidation(Exp_StatusCode);
		 * 
		 * if(Integer.parseInt(Exp_StatusCode) ==201) {
		 * createBatchReq.SchemaValidation(); }
		 * 
		 * 
		 * }
		 */
	 /*****************************NEW Approach**********************/
	 
	 
	 
	 @Given("Admin creates POST request body for {string}")
	 public void admin_creates_post_request_body_for(String testCaseID) throws Exception {
	    System.out.println(">>>>>>>>> in Given :");
	    // Load test data
        createProgramRequest.loadTestData(EXCEL_PATH, SHEET_NAME);

        // Optional: Print loaded test data for debugging
        createProgramRequest.printTestData();

        // Verify requested TestCaseID exists
        CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
        
        System.out.println("Loaded Test Data for TestCaseID " + testCaseID + ": " +
                           programData.getProgramName() + ", " + programData.getProgramDescription());
	 }
	 @When("Admin sends POST request to create a program for {string}")
	 public void admin_sends_post_request_to_create_a_program(String testCaseID) throws Exception {
		
		 response = createProgramRequest.sendPostRequest(testCaseID);
		 
//
//		// Retrieve program data for the specified TestCaseID
//	        CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
//
//	        // Serialize to JSON for debugging
//	        String jsonBody = new ObjectMapper().writeValueAsString(programData);
//	        System.out.println("Serialized JSON: " + jsonBody);
//
//	        // Send the POST request
//	        RestAssured.baseURI = BASE_URI;
//	        response = RestAssured.given()
//	                .header("Content-Type", "application/json")
//	                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZGV0QGdtYWlsLmNvbSIsImlhdCI6MTczMjI1ODIxMywiZXhwIjoxNzMyMjg3MDEzfQ.YKUzOyT1gVz4Gw3Y6l9HtWjaASay6LTdeTyvJjt3IY2fYoueKCnap6sLHZH5RVMidgkdqSyS0zJFWr-ClPO1uw")
//	                .body(programData) // Automatically serializes POJO to JSON
//	                .post(CommonUtils.createProgramEndPoint); // Replace with your endpoint
	    }
	 
	 
	 @Then("Response status code should be {string}")
	 public void response_status_code_should_be(String expectedStatusCode) {
	     
		 
		 Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
	 }
	 
}
