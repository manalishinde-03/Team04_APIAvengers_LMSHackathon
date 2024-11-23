package api.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateProgramRequest extends CommonUtils {

	public Response response;
	private RequestSpecification request;
	int statusCode;
	String statusLine;
	public int programID;
	public String programName;
	public String programStatus;
	CreateProgramRequestPojo createProgramPojo = new CreateProgramRequestPojo();

	private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();


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

//	    public void printTestData() {
//	        testDataMap.forEach((key, value) -> {
//	            System.out.println("TestCaseID: " + key + ", ProgramName: " + value.getProgramName() +
//	                               ", ProgramDescription: " + value.getProgramDescription());
//	        });
//	    }

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

	
//Generic SendRequest method

	public Response sendPostRequestGeneric(String endpoint, Object requestBody, Map<String, String> headers,
			int expectedStatusCode) {
		System.out.println("Sending POST request to endpoint: " + endpoint);

		// Build the request
		RestAssured.baseURI = CommonUtils.baseURI;
		RequestSpecification request = RestAssured
				.given();
		if (headers != null) {
			request.headers(headers);
		}
		if (requestBody != null) {
			request.body(requestBody);
		}
		
		response = request
				.post(endpoint);

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());


		return response;
	}

	public Response sendhttpMethod(String httpReq, String testCaseID) {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndpoint();

		
		RestAssured.baseURI = CommonUtils.baseURI;
		System.out.println(" Invalid Method :" +httpReq);
		
		switch(httpReq.toUpperCase()) {
		
		case "GET" :
			response = RestAssured
			.given().header("Content-Type", "application/json")
			.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
			.get(endpoint);
			 break;
		
		case "PUT" :
		response = RestAssured
		.given().header("Content-Type", "application/json")
		.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
		.put(endpoint);
		break;
		 default:
	            throw new IllegalArgumentException("Unsupported HTTP method: " + httpReq);
	}

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());
		
		return response;
	}

}
