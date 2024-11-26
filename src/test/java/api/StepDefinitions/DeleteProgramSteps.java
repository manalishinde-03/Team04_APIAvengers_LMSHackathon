package api.StepDefinitions;

import static api.StepDefinitions.ProgramCreateSteps.EXCEL_PATH;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.Pojo.CreateProgramRequestPojo;
import api.Request.CreateProgramRequest;
import api.Request.DeleteProgramRequest;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteProgramSteps extends CommonUtils {
	
	CreateProgramRequest createProgramRequest = new CreateProgramRequest();
	CreateProgramRequestPojo createProgramPojo = new CreateProgramRequestPojo();
	public Response response;
	String DataRequestBody;
	ObjectMapper objectMapper = new ObjectMapper();
	
	 private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();
	 
	 private static final Logger log = LogManager.getLogger(DeleteProgramSteps.class);

	    public static final String EXCEL_PATH = CommonUtils.excelFilePath;
	    private static final String SHEET_NAME = "Program";
	
	    DeleteProgramRequest delProgramRequest = new DeleteProgramRequest();
	    private static final Logger logger = LogManager.getLogger(DeleteProgramSteps.class);


	    @Given("Admin creates DELETE request for {string}")
	    public void admin_creates_delete_request_for(String testCaseId) throws Exception {
	    	delProgramRequest.loadTestData(EXCEL_PATH, "Program");
	        CreateProgramRequestPojo createProgramRequestPojo = delProgramRequest.getProgramData(testCaseId);
	        delProgramRequest.buildRequest(createProgramRequestPojo);
	    }

	    @When("Admin sends DELETE request for {int} with programID and valid endpoint for {string}")
	    public void admin_sends_delete_request_for_with_program_id_and_valid_endpoint_for(int progIndex, String testCaseId) {
	    	response = delProgramRequest.deleteProgramByIDReq(progIndex,testCaseId);
	    }

	    @When("Admin sends DELETE request for {int} with invalid Program ID for {string}")
	    public void admin_sends_delete_request_for_with_invalid_program_id_for(int progIndex, String testCaseId) {
	    	response = delProgramRequest.deleteProgramByIDReq(progIndex,testCaseId);
	    	
	    }
	    
	    @When("Admin sends DELETE request for {int} with programName and valid endpoint for {string}")
	    public void admin_sends_delete_request_for_with_program_name_and_valid_endpoint_for(int progIndex, String testCaseId) {
	    	response = delProgramRequest.deleteProgramByNameReq(progIndex,testCaseId);
	    }

	    @When("Admin sends DELETE request for {int} with invalid Program Name for {string}")
	    public void admin_sends_delete_request_for_with_invalid_program_name_for(int progIndex, String testCaseId) {
	    	response = delProgramRequest.deleteProgramByNameReq(progIndex,testCaseId);
	    }
	
}
