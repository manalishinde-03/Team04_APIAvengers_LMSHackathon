package api.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Payload.CreateProgramPayload;
import api.Payload.UserLoginPayload;
import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteProgramRequest extends CommonUtils {

	public Response response;
	private RequestSpecification request;
	int statusCode;
	String statusLine;
	public int programID;
	public String programName;
	public String programStatus;
	CreateProgramRequestPojo createProgramPojo = new CreateProgramRequestPojo();

	private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

	  public RequestSpecification buildRequest(CreateProgramRequestPojo createProgramRequestPojo) {
	        RestAssured.baseURI = baseURI;
	        

	        request = RestAssured
	        		.given()
	                .header("Content-Type", "application/json");

	        if(!createProgramRequestPojo.getAction().contains("NoAuth")) {
	           // request.auth().oauth2(getAdminToken());
	        	
	        	request
	        	.header("Authorization", "Bearer " + CommonUtils.getAdminToken());
	            System.out.println(">>Received auth token : "+getAdminToken());
	        }

	        System.out.println("Request :" + request.log().all());
	        return request;
	    }

	public void loadTestData(String excelPath, String sheetName) throws Exception {
		if (testDataMap.isEmpty()) {
			List<CreateProgramRequestPojo> programDataList = ExcelReader.readTestData(excelPath, sheetName);
			for (CreateProgramRequestPojo programData : programDataList) {
				testDataMap.put(programData.getTestCaseId(), programData);
			}
		}
	}

	// Method to get program data for a given TestCaseID
	public CreateProgramRequestPojo getProgramData(String testCaseID) {
		if (!testDataMap.containsKey(testCaseID)) {
			throw new RuntimeException("Test case ID not found: " + testCaseID);
		}
		return testDataMap.get(testCaseID);
	}
	
	public Response deleteProgramByIDReq(int progIndex,String testCaseID) {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndpoint();

		//String jsonBody = new ObjectMapper().writeValueAsString(programData);
		//System.out.println("JSON for TestCaseID " + testCaseID + ": " + jsonBody);

		System.out.println("Using Endpoint: " + endpoint);
		 if (programData.getEndpoint().contains("{") && programData.getEndpoint().contains("}")) {

		RestAssured.baseURI = CommonUtils.baseURI;
		 response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.delete(endpoint,getProgramID().get(progIndex));
		 
		 }

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		return response;
	}
	
	public Response sendPostRequest(String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndpoint();

		String jsonBody = new ObjectMapper().writeValueAsString(programData);
		System.out.println("JSON for TestCaseID " + testCaseID + ": " + jsonBody);

		System.out.println("Using Endpoint: " + endpoint);

		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				 .body(programData)
				.post(endpoint);

		
		if(response.getStatusCode()==201) {
			
			JsonPath jsonPath = response.jsonPath();
			if (jsonPath.get("programId") != null) {
				programID = jsonPath.getInt("programId");
				System.out.println("Program ID from response: " + programID);
				
				CommonUtils.setProgramID(programID); 
				
			} else {
				System.out.println("No Program ID found in the response.");
			}
			
			
			programName = response.jsonPath().getString("programName");
			System.out.println("Program Name from response: " + programName);
			CommonUtils.setProgramName(programName);
			
		}
		
		programStatus = response.jsonPath().getString("programStatus");

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

}
