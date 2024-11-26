package api.Request;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.Pojo.CreateProgramRequestPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
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

	private static final Logger log = LogManager.getLogger(CreateProgramRequest.class);

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

	public RequestSpecification buildRequest(CreateProgramRequestPojo createProgramRequestPojo) {
		RestAssured.baseURI = baseURI;
		request = RestAssured.given().header("Content-Type", "application/json");

		if (!createProgramRequestPojo.getAction().contains("NoAuth")) {
			request.header("Authorization", "Bearer " + CommonUtils.getAdminToken());
		} else {
			request.baseUri(baseURI);
		}

		log.info("Request :" + request.log().toString());
		return request;
	}

	public Response deleteProgramByIDReq(int progIndex, String testCaseID) {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndPoint();

		log.info("Using Endpoint: " + endpoint);
		if (programData.getEndPoint().contains("{") && programData.getEndPoint().contains("}")) {

			RestAssured.baseURI = CommonUtils.baseURI;
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
					.delete(endpoint, getProgramID().get(progIndex));

		} else {
			RestAssured.baseURI = CommonUtils.baseURI;
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + CommonUtils.getAdminToken()).delete(endpoint);

		}

		log.info("Response Body: " + response.getBody().asPrettyString());

		return response;
	}

	public Response deleteProgramByNameReq(int progIndex, String testCaseID) {

		CreateProgramRequestPojo programData = getProgramData(testCaseID);
		String endpoint = programData.getEndPoint();

		response = RestAssured.delete(endpoint, getProgramName().get(progIndex));

		log.info("Response Status Code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().asPrettyString());
		if (response.getBody().asString().contains("message")) {
			log.info("Message: " + response.jsonPath().getString("message"));

		}
		return response;

	}

}
