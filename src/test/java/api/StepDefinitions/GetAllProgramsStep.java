package api.StepDefinitions;

import api.Pojo.CreateProgramRequestPojo;

import api.Request.GetAllProgramRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static api.StepDefinitions.ProgramCreateSteps.EXCEL_PATH;

import lombok.extern.log4j.Log4j2;

import static api.StepDefinitions.ProgramCreateSteps.excelFilePath;

@Log4j2
public class GetAllProgramsStep {

    GetAllProgramRequest allProgramRequest = new GetAllProgramRequest();
    public Response response;
    @Given("Admin creates GET Program request for the LMS API for {string}")
    public void adminCreatesGETProgramRequestForTheLMSAPIFor(String testCaseId) throws Exception {

    	allProgramRequest.loadTestData(excelFilePath, "Program");
        CreateProgramRequestPojo createProgramRequestPojo = allProgramRequest.getProgramData(testCaseId);
        allProgramRequest.buildRequest(createProgramRequestPojo);
    }

    @When("Admin sends GET request with endpoint for {string}")
    public void adminSendsGETRequestWithEndpointFor(String testCaseId) {
        response = allProgramRequest.sendGetRequest(allProgramRequest.getProgramData(testCaseId));
    }

    @Then("Admin receives {string} status.")
    public void adminReceivesStatus(String code) {

    	log.info("Response body: {}", response.body());
        allProgramRequest.response.then().assertThat().statusCode(Integer.parseInt(code));
    }
}
