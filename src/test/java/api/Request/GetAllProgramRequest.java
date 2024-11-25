package api.Request;

import api.Pojo.CreateProgramRequestPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;

public class GetAllProgramRequest extends CommonUtils {

    public Response response;
    private RequestSpecification request;
    int statusCode;
    String statusLine;
    private final static HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

    public RequestSpecification buildRequest(CreateProgramRequestPojo createProgramRequestPojo) {
        RestAssured.baseURI = baseURI;

        request = RestAssured.given()
                .header("Content-Type", "application/json");

        if(!createProgramRequestPojo.getAction().contains("NoAuth")) {
            request.auth().oauth2(getAdminToken());
        }

        if (createProgramRequestPojo.getAction().contains("InvalidUri")) {
            request.baseUri("https://lms-hackthon-oct24-3efc7e0df381.herokuapp.com/lmsInvalid");
        } else {
            request.baseUri(baseURI);
        }

        System.out.println("Request :" + request.log().all());
        return request;
    }

    public Response sendGetRequest(CreateProgramRequestPojo createProgramRequestPojo) {

        if (createProgramRequestPojo.getEndpoint().contains("{") && createProgramRequestPojo.getEndpoint().contains("}")) {
            response = request.when().get(createProgramRequestPojo.getEndpoint(), getProgramID());
        } else if(createProgramRequestPojo.getMethod().contains("Post")) {
            response = request.when().post(createProgramRequestPojo.getEndpoint());
        } else {
            response = request.when().get(createProgramRequestPojo.getEndpoint());
        }


        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        System.out.println("Response :" + response.asPrettyString());
        System.out.println("StatusCode :" + statusCode);

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
}


