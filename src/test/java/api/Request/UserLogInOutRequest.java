package api.Request;

import api.Pojo.LogInOutRequestPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class UserLogInOutRequest extends CommonUtils {

    public static String adminToken;
    public Response response;
    private RequestSpecification request;
    int statusCode;
    String statusLine;
    private HashMap<String, LogInOutRequestPojo> testDataMap = new HashMap<>();

    public void buildRequest(LogInOutRequestPojo logInOutRequestPojo) {
        request = RestAssured.given()
                .baseUri(logInOutRequestPojo.getBaseUri())
                .header("Content-Type", "application/json");

        if("WithAuth".equalsIgnoreCase(logInOutRequestPojo.getAction())) {
            request.auth().oauth2(getAdminToken());
        }
        System.out.println("Request :" + request.log().all());
    }

    public Response sendGetRequest(LogInOutRequestPojo logInOutRequestPojo) {

        response = request.when().get(logInOutRequestPojo.getEndPoint());
        handleResponse(response, logInOutRequestPojo);
        return response;
    }

    public Response sendPostRequest(LogInOutRequestPojo logInOutRequestPojo) {
        response = request.body(logInOutRequestPojo).when().post(logInOutRequestPojo.getEndPoint());
        System.out.println("Response :" + response.print());
        handleResponse(response, logInOutRequestPojo);
        return response;
	}

    private void handleResponse(Response response, LogInOutRequestPojo logInOutRequestPojo) {
        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        if("StoreToken".equalsIgnoreCase(logInOutRequestPojo.getAction())) {
            adminToken = response.jsonPath().getString("token");
            setAdminToken(adminToken);
        }
        System.out.println("Response :" + response.asPrettyString());
        System.out.println("Token :" + adminToken);
        System.out.println("StatusCode :" + statusCode);
    }

    public void loadTestData(String excelPath, String sheetName) throws Exception {
        testDataMap = ExcelReader.readTestDataLoginLogout(excelPath, sheetName);
    }

    public LogInOutRequestPojo getLoginRequestData(String testCaseID) {
        if (!testDataMap.containsKey(testCaseID)) {
            throw new RuntimeException("Test case ID not found: " + testCaseID);
        }
        return testDataMap.get(testCaseID);
    }
}
