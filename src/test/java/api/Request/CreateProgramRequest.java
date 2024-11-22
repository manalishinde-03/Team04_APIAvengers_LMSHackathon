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

	public RequestSpecification buildRequest() {
		RestAssured.baseURI = baseURI;
		createProgramPojo = CreateProgramPayload.createProgram();

		request = RestAssured.given().baseUri(baseURI)
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Content-Type", "application/json")
				.body(createProgramPojo);

		System.out.println("POST Program Request :" + request.log().all());
		return request;

	}

	public RequestSpecification buildRequestWithMandatoryDetails() {
		RestAssured.baseURI = baseURI;
		createProgramPojo = CreateProgramPayload.createProgramWithMandatoryDetails();

		request = RestAssured.given().baseUri(baseURI).header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Content-Type", "application/json").body(createProgramPojo);

		System.out.println("POST Program Request :" + request.log().all());
		return request;

	}

	public RequestSpecification buildRequest(String user) {
		RestAssured.baseURI = baseURI;
		createProgramPojo = CreateProgramPayload.createProgram();
		switch (user) {

		case "Admin":
			request = RestAssured.given().header("Authorization", "Bearer " + CommonUtils.getAdminToken())
					.header("Content-Type", "application/json");
			break;
		case "NoAuth":
			request = RestAssured.given().header("Authorization", "No Auth").header("Content-Type", "application/json");
			break;
		default:
			throw new IllegalArgumentException("Unsupported user type: " + user);
		}
		return request;
	}

	public Response sendRequest() {

		response = request.when().post(createProgramEndPoint);

		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();

		programID = response.jsonPath().getInt("programId");
		programName = response.jsonPath().getString("programName");
		programStatus = response.jsonPath().getString("programStatus");

		System.out.println("Response :" + response.asPrettyString());
		System.out.println("status Code :" + statusCode);
		System.out.println("status Line :" + statusLine);
		programName = response.jsonPath().getString("programName");
		programStatus = response.jsonPath().getString("programStatus");

		CommonUtils.setProgramID(programID);
		CommonUtils.setProgramName(programName);
		CommonUtils.setProgramStatus(programStatus);

		return response;
	}

	public Response sendRequestWithNoAuth() {

		response = request.when().post(createProgramEndPoint);

		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();

		return response;
	}

	public RequestSpecification buildRequestExcel() {

		RestAssured.baseURI = baseURI;
		createProgramPojo = CreateProgramPayload.createProgram();

		request = RestAssured.given().baseUri(baseURI)
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Content-Type", "application/json")
				.body(createProgramPojo);

		System.out.println("POST Program Request :" + request.log().all());

		return request;

	}

	public Response sendRequestExcel() {

		response = request.when().post(createProgramEndPoint);

		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();

		programID = response.jsonPath().getInt("programId");
		programName = response.jsonPath().getString("programName");
		programStatus = response.jsonPath().getString("programStatus");

		System.out.println("Response :" + response.asPrettyString());
		System.out.println("status Code :" + statusCode);
		System.out.println("status Line :" + statusLine);
		programName = response.jsonPath().getString("programName");
		programStatus = response.jsonPath().getString("programStatus");

		CommonUtils.setProgramID(programID);
		CommonUtils.setProgramName(programName);
		CommonUtils.setProgramStatus(programStatus);

		return response;
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

	    public void printTestData() {
	        testDataMap.forEach((key, value) -> {
	            System.out.println("TestCaseID: " + key + ", ProgramName: " + value.getProgramName() +
	                               ", ProgramDescription: " + value.getProgramDescription());
	        });
	    }

	 
	    public Response sendPostRequest(String testCaseID) throws Exception {
	        CreateProgramRequestPojo programData = getProgramData(testCaseID);

	        String jsonBody = new ObjectMapper().writeValueAsString(programData);
	        System.out.println("JSON for TestCaseID " + testCaseID + ": " + jsonBody);

	        RestAssured.baseURI =CommonUtils.baseURI;
	        Response response = RestAssured.given()
	                .header("Content-Type", "application/json")
	                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZGV0QGdtYWlsLmNvbSIsImlhdCI6MTczMjI2MDc5MSwiZXhwIjoxNzMyMjg5NTkxfQ.93kwb_4sG3HOy3wr6YWWiHqpKKfSB153RqgIVttbcO1i0pF4Polh8od6zn27slYyyz8RFO2bdqjwnhDlZC2Vjg")
	                .body(programData)
	                .post(CommonUtils.createProgramEndPoint);

	        System.out.println("Response Status Code: " + response.getStatusCode());
	        System.out.println("Response Body: " + response.getBody().asString());
	        
	        programID = response.jsonPath().getInt("programId");
			programName = response.jsonPath().getString("programName");
			programStatus = response.jsonPath().getString("programStatus");
			
			CommonUtils.setProgramID(programID);
			CommonUtils.setProgramName(programName);
			CommonUtils.setProgramStatus(programStatus);

	        return response;
	    }
}
