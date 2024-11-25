package api.Request;

import api.Pojo.LogInOutRequestPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class UserLogInOutRequest extends CommonUtils {

    public static String adminToken;
    public Response response;
    private RequestSpecification request;
    int statusCode;
    String statusLine;
    private HashMap<String, LogInOutRequestPojo> testDataMap = new HashMap<>();

    public UserLogInOutRequest() {
    }

    public void buildRequest(LogInOutRequestPojo logInOutRequestPojo) {
    //    log.info("Creating request for: {}", logInOutRequestPojo);
        request = RestAssured.given()
                .baseUri(logInOutRequestPojo.getBaseUri())
                .header("Content-Type", "application/json");

        if("WithAuth".equalsIgnoreCase(logInOutRequestPojo.getAction())) {
            request.auth().oauth2(getAdminToken());
        }
     //   log.info("Request :" + request.log().all());
    }

    public Response sendGetRequest(LogInOutRequestPojo logInOutRequestPojo) {

        response = request.when().get(logInOutRequestPojo.getEndPoint());
        handleResponse(response, logInOutRequestPojo);
        return response;
    }

    public Response sendPostRequest(LogInOutRequestPojo logInOutRequestPojo) {
        response = request.body(logInOutRequestPojo).when().post(logInOutRequestPojo.getEndPoint());
       // log.info("Response :" + response.print());
        handleResponse(response, logInOutRequestPojo);
        return response;
	}

    private void handleResponse(Response response, LogInOutRequestPojo logInOutRequestPojo) {
        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        if("StoreToken".equalsIgnoreCase(logInOutRequestPojo.getAction())) {
            adminToken = response.jsonPath().getString("token");
            setAdminToken(adminToken);
           // log.info("Response :" + response.asPrettyString());
        }

       // log.info("Token :" + adminToken);
       // log.info("StatusCode :" + statusCode);
    }

    public void loadTestData(String excelPath, String sheetName) throws Exception {
       // log.info("Loading excel data from {} sheet {}", excelPath, sheetName);
        testDataMap = ExcelReader.readTestDataLoginLogout(excelPath, sheetName);
    }

    public LogInOutRequestPojo getLoginRequestData(String testCaseID) {
        if (!testDataMap.containsKey(testCaseID)) {
          //  log.error("Test case ID {} not found", testCaseID);
            throw new RuntimeException("Test case ID not found: " + testCaseID);
        }
        return testDataMap.get(testCaseID);
    }
}
