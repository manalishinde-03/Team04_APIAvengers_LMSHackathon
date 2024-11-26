package api.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.Pojo.CreateProgramRequestPojo;
import api.StepDefinitions.ProgramCreateSteps;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
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
	private static final Logger log = LogManager.getLogger(CreateProgramRequest.class);

	private HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

	public RequestSpecification buildRequest(CreateProgramRequestPojo createProgramRequestPojo) {
		RestAssured.baseURI = baseURI;

		request = RestAssured.given().header("Content-Type", "application/json");

		if (!createProgramRequestPojo.getAction().contains("NoAuth")) {

			request.header("Authorization", "Bearer " + CommonUtils.getAdminToken());
			log.info(">>Received auth token : " + getAdminToken());
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
		String endpoint = programData.getEndPoint();

		log.info("Using Endpoint: " + endpoint);

		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured.given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken()).body(programData).post(endpoint);

		if (response.getStatusCode() == 201) {

			JsonPath jsonPath = response.jsonPath();
			if (jsonPath.get("programId") != null) {
				programID = jsonPath.getInt("programId");
				log.info("Program ID from response: " + programID);

				CommonUtils.setProgramID(programID);

			} else {
				log.info("No Program ID found in the response.");
			}

			programName = response.jsonPath().getString("programName");
			log.info("Program Name from response: " + programName);
			CommonUtils.setProgramName(programName);

		}

		programStatus = response.jsonPath().getString("programStatus");

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

//Generic SendRequest method

	public Response sendPostRequestGeneric(String endpoint, Object requestBody, Map<String, String> headers,
			int expectedStatusCode) {

		RestAssured.baseURI = CommonUtils.baseURI;
		RequestSpecification request = RestAssured.given();
		if (headers != null) {
			request.headers(headers);
		}
		if (requestBody != null) {
			request.body(requestBody);
		}

		response = request.post(endpoint);

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

	public Response sendhttpMethod(String httpReq, String testCaseID) {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndPoint();

		RestAssured.baseURI = CommonUtils.baseURI;
		log.info(" Invalid Method :" + httpReq);

		switch (httpReq.toUpperCase()) {

		case "GET":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + CommonUtils.getAdminToken()).get(endpoint);
			break;

		case "PUT":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + CommonUtils.getAdminToken()).put(endpoint);
			break;
		default:
			throw new IllegalArgumentException("Unsupported HTTP method: " + httpReq);
		}

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

	// Method overloading
	public Response sendPUTRequest(int progIndex, String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndPoint();
		String endpointByName = endpoint.replace("{programName}", CommonUtils.getProgramName().get(progIndex));

		response = request.when().body(programData).put(endpointByName);

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

	public Response sendPUTRequest(String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);

		String endpoint = programData.getEndPoint();
		String endpointByName = endpoint.replace("{programName}", programData.getProgramName());

		response = request.when().body(programData).put(endpointByName);

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

	public Response sendPUTRequestNoAuth(String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);

		String endpoint = programData.getEndPoint();
		String endpointByName = endpoint.replace("{programName}", programData.getProgramName());

		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured.given().header("Content-Type", "application/json")
				.header("Authorization", "No Auth").body(programData).put(endpointByName);

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

	public Response sendPUTRequestByID(int progIndex, String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String programId = CommonUtils.getProgramID().get(progIndex).toString();
		String endpoint = programData.getEndPoint();
		String endpointByID = endpoint.replace("{programId}", programId);

		RestAssured.baseURI = CommonUtils.baseURI;
		Response response = RestAssured.given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken()).body(programData).put(endpointByID);

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;

	}

	public Response sendPUTRequestByIDInvalidTC(int progIndex, String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);

		if (programData.getEndPoint().contains("{") && programData.getEndPoint().contains("}")) {

			response = request.when().header("Authorization", "Bearer " + CommonUtils.getAdminToken()).body(programData)
					.put(programData.getEndPoint(), getProgramID().get(progIndex));
		} else

		if (programData.getMethod().contains("Get")) {
			response = request.when().header("Authorization", "Bearer " + CommonUtils.getAdminToken()).body(programData)
					.get(programData.getEndPoint());
		} else {

			response = request.when().header("Authorization", "Bearer " + CommonUtils.getAdminToken()).body(programData)
					.put(programData.getEndPoint());
		}

		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();

		log.info("Response :" + response.asPrettyString());
		log.info("StatusCode :" + statusCode);

		return response;

	}

	public Response sendPUTRequestByIDInvalidMethod(int progIndex, String testCaseID) throws Exception {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);

		if (programData.getEndPoint().contains("{") && programData.getEndPoint().contains("}")) {

			if (programData.getMethod().contains("Get")) {
				response = request.when().header("Authorization", "Bearer " + CommonUtils.getAdminToken())
						.body(programData).get(programData.getEndPoint(), getProgramID().get(progIndex));
			}

		}

		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();

		log.info("Response :" + response.asPrettyString());
		log.info("StatusCode :" + statusCode);

		return response;

	}

}
