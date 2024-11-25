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

	  public RequestSpecification buildRequest(CreateProgramRequestPojo createProgramRequestPojo) {
	        RestAssured.baseURI = baseURI;
	        
	        System.out.println(">>>>> Value from Action column :" +createProgramRequestPojo.getAction());
	        System.out.println(">>>>> Value from Method column :" +createProgramRequestPojo.getMethod());

	        request = RestAssured.given()
	                .header("Content-Type", "application/json");

	        if(!createProgramRequestPojo.getAction().contains("NoAuth")) {
	           // request.auth().oauth2(getAdminToken());
	        	
	        	request.header("Authorization", "Bearer " + CommonUtils.getAdminToken());
	            System.out.println(">>Received auth token : "+getAdminToken());
	        }

	        if (createProgramRequestPojo.getAction().contains("InvalidUri")) {
	            request.baseUri("https://lms-hackthon-oct24-3efc7e0df381.herokuapp.com/lmsInvalid");
	        } else {
	            request.baseUri(baseURI);
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

	public Response sendPostRequest(String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndpoint();

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
	
	//Method overloading
	public Response sendPUTRequest(int progIndex,String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndpoint();
		String endpointByName = endpoint.replace("{programName}", CommonUtils.getProgramName().get(progIndex));

		System.out.println("endpointByName >>>>> "+endpointByName);
		
		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				 .body(programData)
				.put(endpointByName);

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		return response;
	}
	
	public Response sendPUTRequest(String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		
		System.out.println(">>>>>>>>>> Prog name - " +programData.getProgramName());
		String endpoint = programData.getEndpoint();
		String endpointByName = endpoint.replace("{programName}", programData.getProgramName());

		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				//.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZGV0QGdtYWlsLmNvbSIsImlhdCI6MTczMjQzMjY3NCwiZXhwIjoxNzMyNDYxNDc0fQ.tfgrK_BIxiGDro0QddzQn4JA0lL3nnvOlo4bZvN4W65M-d4dynIY9kcsgf-3RCJW68RZAP9siDq1SFdIjcfq2Q")
				 .body(programData)
				.put(endpointByName);

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		return response;
	}
	
	public Response sendPUTRequestNoAuth(String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		
		String endpoint = programData.getEndpoint();
		String endpointByName = endpoint.replace("{programName}", programData.getProgramName());

		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				//.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Authorization", "No Auth")
				 .body(programData)
				.put(endpointByName);

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		return response;
	}
	
	public Response sendPUTRequestByID(int progIndex,String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
			String programId = CommonUtils.getProgramID().get(progIndex).toString();
			String endpoint = programData.getEndpoint();
			String endpointByID = endpoint.replace("{programId}", programId);

			System.out.println("endpointByID >>>>> "+endpointByID);
		
		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				 .body(programData)
				.put(endpointByID);

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		return response;
		
	}
	
	public Response sendPUTRequestByIDInvalidTC(int progIndex,String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);

		        if (programData.getEndpoint().contains("{") && programData.getEndpoint().contains("}")) {
		            
		        	System.out.println("***************>> in 1st if ...........");
		        	response = request
		            		.when()
		            		.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
		            		.body(programData)
		            		.put(programData.getEndpoint(), getProgramID().get(progIndex));
		        } else 
		        	
		        	if(programData.getMethod().contains("Get")) {
		        	System.out.println("***************>> in 2nd if ...........");
		            response = request
		            		.when()
		            		.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
		            		.body(programData)
		            		.get(programData.getEndpoint());
		        } else {
		        	
		        	System.out.println("***************>> in 3rd if ...........");
		            response = request
		            		.when()
		            		.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
		            		.body(programData)
		            		.put(programData.getEndpoint());
		        }


		        statusCode = response.getStatusCode();
		        statusLine = response.getStatusLine();

		        System.out.println("Response :" + response.asPrettyString());
		        System.out.println("StatusCode :" + statusCode);

		        return response;
		       
		    }
	
	public Response sendPUTRequestByIDInvalidMethod(int progIndex,String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);

		        if (programData.getEndpoint().contains("{") && programData.getEndpoint().contains("}")) {
		        	
		        	if(programData.getMethod().contains("Get")) {
			        	System.out.println("***************>> in if ...........");
			            response = request
			            		.when()
			            		.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
			            		.body(programData)
			            		.get(programData.getEndpoint(),getProgramID().get(progIndex));
			        }
		            
		        }

		        statusCode = response.getStatusCode();
		        statusLine = response.getStatusLine();

		        System.out.println("Response :" + response.asPrettyString());
		        System.out.println("StatusCode :" + statusCode);

		        return response;
		       
		    }
		
	
	
	
}
