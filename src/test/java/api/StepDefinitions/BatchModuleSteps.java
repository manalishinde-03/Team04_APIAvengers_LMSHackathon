package api.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.Request.BatchModuleRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BatchModuleSteps {
	
	BatchModuleRequest batchModule= new BatchModuleRequest();	
	
	// Create Batch
	
	@Given("Admin creates POST request body for the Testcases {string}")
	public void admin_creates_post_request_body_for_the_testcases(String TestCase) throws JsonProcessingException, IOException {
		batchModule.createBatchbuildRequest(TestCase);
	}
	
	
	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		batchModule.CreateBatchSendRequest();
	}

	
	@Then("Admin receives respective status codes {string} for the TestCases")
	public void admin_receives_respective_status_codes_for_the_test_cases(String Exp_StatusCode) {
		
		batchModule.createBatchResponseValidation(Exp_StatusCode);
		
	}
	
	
	// Get All batches
	
	@Given("Admin creates GET Request for the {string}")
	public void admin_creates_get_request_for_the(String GetTestCases) throws IOException {
		batchModule.buildGetallBatchrequest(GetTestCases);
		
	}

	@When("Admin sends the GetAll HTTPS Request with endpoint")
	public void admin_sends_the_get_all_https_request_with_endpoint() {
		batchModule.batch_allGetRequest("GetAllBatches");
	}

	@Then("Admin receives status code with response body {string}")
	public void admin_receives_status_code_with_response_body(String Expcode) {		
		batchModule.GetAllBatchREsponseValidation(Expcode);	
		
	}
	
	
	// Get batch by BatchID
	
	@Given("Admin creates GET Request for the GetBy BatchID {string}")
	public void admin_creates_get_request_for_the_get_by_batch_id(String ByBatchID) throws IOException {
		batchModule.buildGetByBatchID(ByBatchID);
	}
	@When("Admin sends the HTTPS Request with GetBy BatchID endpoint")
	public void admin_sends_the_https_request_with_get_by_batch_id_endpoint() {
		batchModule.batch_allGetRequest("BatchByBATCHID");
	}
	
	@Then("Admin receives status code with response body GetBy BatchID {string}")
	public void admin_receives_status_code_with_response_body_get_by_batch_id(String ExpCode) {		
		batchModule.getBybatchIDValidation(ExpCode);
		
	}
	
	
	
	// Get Batch by Batch Name  
	
	@Given("Admin creates GET Request for the GetBy BatchName {string} Request")
	public void admin_creates_get_request_for_the_get_by_batch_name_request(String BatchNameTestcases) throws IOException {
	    
		batchModule.buildGetByBatchName(BatchNameTestcases);
	}

	@When("Admin sends the HTTPS Request with GetBy BatchName endpoint")
	public void admin_sends_the_https_request_with_get_by_batch_name_endpoint() {
		batchModule.batch_allGetRequest("BatchByBATCHNAME");
	}

	@Then("Admin receives status code with response body GetBy BatchName {string}")
	public void admin_receives_status_code_with_response_body_get_by_batch_name(String Expcode) {
		
		batchModule.getByBatchName_MessageValidation(Expcode);
	}
	
	
	// Get Batch by Program ID
	
	
	
	@Given("Admin creates GET Request for the GetBy ProgramID {string} Request")
	public void admin_creates_get_request_for_the_get_by_program_id_request(String byPRGMIDtestcases) throws IOException {
		batchModule.buildGetByPGMId(byPRGMIDtestcases);
	}
	@When("Admin sends the HTTPS Request with GetBy ProgramID endpoint")
	public void admin_sends_the_https_request_with_get_by_program_id_endpoint() {
		batchModule.batch_allGetRequest("BatchByPGMID");
	}
	@Then("Admin receives status code with response body GetBy ProgramID {string}")
	public void admin_receives_status_code_with_response_body_get_by_program_id(String ExpCode) {
		batchModule.getBatchByProgramIDvalidation(ExpCode);
	}
	
	
	// Delete by Batch ID request	
	
	
	@Given("Admin creates DELETE Request By BatchId {string}")
	public void admin_creates_delete_request_by_batch_id(String DelByBatchIDtestCases) throws IOException {
		batchModule.buildDelByBatchIdrequest(DelByBatchIDtestCases);
	    
	}
	@When("Admin sends HTTPS Request  with Del By BatchId Endpoint")
	public void admin_sends_https_request_with_del_by_batch_id_endpoint() {
		batchModule.sendRequestDelByBatchID();
	   
	}
	@Then("Admin receives status code with success message for DelByBatchID request {string}")
	public void admin_receives_status_code_with_success_message_for_del_by_batch_id_request(String delExpStatusCode) {
	    
		batchModule.deleteBybatchIDValidation(delExpStatusCode);
	}
	
	
	
	// Update Batch by Batch ID
	
	@Given("Admin creates PUT Request By BatchId {string}")
	public void admin_creates_put_request_by_batch_id(String UpdateByBatchIDTestcases) throws IOException {
		batchModule.buildUpdateByBatchIDrequest(UpdateByBatchIDTestcases);
	}
	@When("Admin sends HTTPS Request with Update By BatchId Endpoint")
	public void admin_sends_https_request_with_update_by_batch_id_endpoint() {
		batchModule.sendRequestupdateBybatchID();
	}
	@Then("Admin receives status code with response message for UpdateByBatchID request {string}")
	public void admin_receives_status_code_with_response_message_for_update_by_batch_id_request(String UpdateByBatchID_Expcode) {
		batchModule.updateBybatchIdValidation(UpdateByBatchID_Expcode);
	}
	
	
}
