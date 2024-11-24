package api.StepDefinitions;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.Payload.CreateProgramPayload;
import api.Pojo.CreateProgramRequestPojo;
import api.Request.CreateProgramRequest;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProgramCreateSteps extends CommonUtils {
	
	CreateProgramRequest createProgramRequest = new CreateProgramRequest();
	CreateProgramRequestPojo createProgramPojo = new CreateProgramRequestPojo();
	CreateProgramPayload createProgramPayload = new CreateProgramPayload();
	public Response response;
	//public static String baseURI ;
	String DataRequestBody;
	ObjectMapper objectMapper = new ObjectMapper();
	
	 private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

	    private static final String EXCEL_PATH = CommonUtils.excelFilePath;
	    private static final String SHEET_NAME = "Program";
	

	
	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
		System.out.println("Program ID :" +CommonUtils.getProgramID());
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
	

	 /*****************************NEW Approach**********************/
	 
	 
	 
	 @Given("Admin creates POST request body for {string}")
	 public void admin_creates_post_request_body_for(String testCaseID) throws Exception {
	    // Load test data
        createProgramRequest.loadTestData(EXCEL_PATH, SHEET_NAME);

        // Verify requested TestCaseID exists
        CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
        
        System.out.println("Loaded Test Data for TestCaseID " + testCaseID + ": " +
                           programData.getProgramName() + ", " + programData.getProgramDescription());
	 }
	 @When("Admin sends POST request to create a program for {string}")
	 public void admin_sends_post_request_to_create_a_program(String testCaseID) throws Exception {
		
		 response = createProgramRequest.sendPostRequest(testCaseID);
	
	    }
	 
	 @When("Admin sends POST request with No Auth to create a program for {string}")
	 public void admin_sends_post_request_NoAuth_create_program(String testCaseID) throws Exception {
		
		// response = createProgramRequest.sendPostReqNoAuth(testCaseID);
		 
		 Map<String, String> headers = CommonUtils.getHeadersWithoutAuth();
		 CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
		 
		 String endpoint = programData.getEndpoint();
		 System.out.println("Endpoint :"+endpoint);
		 
		response = createProgramRequest.sendPostRequestGeneric(
					endpoint, 
				    programData, 
				    headers, 
				    401
				);
	
	    }
	 
	 @When("Admin sends POST request to an invalid endpoint for {string}")
	 public void admin_sends_post_request_invalidEndpt_create_program(String testCaseID) throws Exception {
			
		// response = createProgramRequest.sendPostReqinvalidEndpt(testCaseID);
		 Map<String, String> headers = CommonUtils.getDefaultHeaders();
		 CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
		 
		 String invalidEndpoint = programData.getEndpoint();
		 System.out.println("Invalid Endpoint :"+invalidEndpoint);
		 
		response = createProgramRequest.sendPostRequestGeneric(
					invalidEndpoint, 
				    programData, 
				    headers, 
				    404
				);
	
	    }
	 
	 @When("Admin sends POST request for creating program with existing programName {string}")
	 public void admin_sends_post_request_existingProgName_create_program(String testCaseID) throws Exception {
			
		// response = createProgramRequest.sendPostReqinvalidEndpt(testCaseID);
		 Map<String, String> headers = CommonUtils.getDefaultHeaders();
		 CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
		 
		 String endpoint = programData.getEndpoint();
		 System.out.println("Invalid Endpoint :"+endpoint);
		 
		response = createProgramRequest.sendPostRequestGeneric(
					endpoint, 
				    programData, 
				    headers, 
				    400
				);
	    }
	 
	 @When("Admin sends {string} request to create program for {string}")
	 public void admin_sends_get_request_invalidMethod_create_program(String httpReq, String testCaseID) throws Exception {
			
		 response = createProgramRequest.sendhttpMethod(httpReq,testCaseID);
		
	    }
	 
	 @When("Admin sends POST request with invalid request body for {string}")
	 public void admin_sends_get_request_invalidReqBody_create_program(String testCaseID) throws Exception {
			
		 Map<String, String> headers = CommonUtils.getDefaultHeaders();
		 CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
		    
		 String endpoint = programData.getEndpoint();
		 System.out.println("Invalid Endpoint :"+endpoint);
		 response = createProgramRequest.sendPostRequestGeneric(endpoint, 
				    programData, 
				    headers, 
				    400);
		
	    }
	 
	 @Given("Admin creates PUT request body for {string}")
	 public void admin_creates_put_request_body_for(String testCaseID) throws Exception {
	    // Load test data
        createProgramRequest.loadTestData(EXCEL_PATH, SHEET_NAME);

        CreateProgramRequestPojo programData = createProgramRequest.getProgramData(testCaseID);
        
        System.out.println("Loaded Test Data for TestCaseID " + testCaseID + ": " +
                           programData.getProgramName() + ", " + programData.getProgramDescription());
	 }
	 
	 @When("Admin sends PUT request with valid request body for {string}")
	 public void admin_sends_put_request_to_create_a_program(String testCaseID) throws Exception {
		
		 response = createProgramRequest.sendPUTRequest(testCaseID);
	
	    }
	 
	 @Then("Response status code should be {string}")
	 public void response_status_code_should_be(String expectedStatusCode) {
	     
		 
		 Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
		 //System.out.println(">>>>>>>Program Name1 from Common Utils :" +CommonUtils.getProgramName());
		 //System.out.println(">>>>>>>Program Name2 from Common Utils :" +CommonUtils.getProgramName().get(1));
	 }
	 
}
