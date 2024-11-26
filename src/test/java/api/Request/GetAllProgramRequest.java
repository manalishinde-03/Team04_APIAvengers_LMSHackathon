package api.Request;

import api.Pojo.CreateProgramRequestPojo;
import api.StepDefinitions.classGetAllSteps;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetAllProgramRequest extends CommonUtils {

    public Response response;
    private RequestSpecification request;
    int statusCode;
    String statusLine;
    private final static HashMap<String, CreateProgramRequestPojo> testDataMap = new HashMap<>();

    private static final Logger log = LogManager.getLogger(GetAllProgramRequest.class);
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

        log.info("Request :" + request.log().all());
        return request;
    }

    public Response sendGetRequest(CreateProgramRequestPojo createProgramRequestPojo) {

 
        if (createProgramRequestPojo.getEndPoint().contains("{") && createProgramRequestPojo.getEndPoint().contains("}")) {
            response = request.when().get(createProgramRequestPojo.getEndPoint(), getProgramID().get(0));
        } else if(createProgramRequestPojo.getMethod().contains("Post")) {
            response = request.when().post(createProgramRequestPojo.getEndPoint());
        } else {
            response = request.when().get(createProgramRequestPojo.getEndPoint());
        }

        if(createProgramRequestPojo.getAction().equalsIgnoreCase("validateSchemaProgramArray")) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/ArrayProgramDto.json"));
        } else if(createProgramRequestPojo.getAction().equalsIgnoreCase("validateSchemaProgramWithUsers")) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/ProgramWithUsersDto.json"));
        } else if(createProgramRequestPojo.getAction().equalsIgnoreCase("validateSchemaProgram")) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/ProgramDto.json"));
        }

        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();
        log.info(statusCode);
        log.info(statusLine);
        
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


