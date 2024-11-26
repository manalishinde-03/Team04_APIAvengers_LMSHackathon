package api.StepDefinitions;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;

import api.Request.getClassRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/*
public class classGetAll extends CommonUtils {

	getClassRequest getRequest = new getClassRequest();
	private RequestSpecification request;
	private Response response;
	private String BatchId="8613";
	//setBatchID(Integer.parseInt(config.getString("BatchID")));

	private String ClassId = "108";
	private String ClassTopic = "TestAlluree";
	private String classStaffId = "U49";
	
	
	
	

	// getAllClass

	@Given("Admin creates GET Request")
	public void admin_creates_get_request() throws IOException {
		RestAssured.baseURI = CommonUtils.baseURI;
	}

	@When("Admin sends HTTPS Request with endpoint for getAllclass")
	public void admin_sends_https_request_with_endpoint() {
		response = getRequest.buildRequest("/allClasses", "get", "Bearer");

		
	}

	@Then("Admin receives {int} OK Status with response body for getAllclass")
	public void admin_receives_ok_status_with_response_body(Integer expectedStatusCode) {

		// Assert.assertNotNull(response, "Response is null.");
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

		// System.out.println("Response Body: " + response.getBody().asString());
	}

	@When("Admin sends HTTPS Request with invalid endpoint for getAllclass")
	public void admin_sends_https_request_with_invalid_endpoint_for_getAllclass() {
		response = getRequest.buildRequest("/alllasses", "get", "Bearer");

	}

	@Then("Admin receives {int} status with error message Not Found for getAllclass")
	public void admin_receives_status_with_error_message_not_found_for_getAllclass(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid method for getAllclass")
	public void admin_sends_https_request_with_invalid_method_for_getAllclass() {
		response = getRequest.buildRequest("/allClasses", "post", "Bearer");

	}

	@Then("Admin receives {int} Method not allowed status visible for getAllclass")
	public void admin_receives_method_not_allowed_status_visible_for_getAllclass(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request without authorization for getAllclass")
	public void admin_sends_https_request_without_authorization_for_get_allclass() {

		response = getRequest.buildRequest("/allClasses", "get", "noauth");

	}

	@Then("Admin receives {int} status with error message Unauthorized for getAllclass")
	public void admin_receives_status_with_error_message_unauthorized_for_get_allclass(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	// Get class recordings by BatchId

	@When("Admin sends HTTPS Request with endpoint for getclassRecordingsBatchID")
	public void admin_sends_https_request_with_endpoint_for_getclassRecordingsBatchID() {
		
	//	BatchID=setBatchID(Integer.parseInt(config.getString("BatchID")));
		String bid = "/batchRecordings/" + BatchId + "";
		response = getRequest.buildRequest(bid, "get", "Bearer");

	}

	@Then("Admin receives {int} OK Status with response body for getclassRecordingsBatchID")
	public void admin_receives_ok_status_with_response_body_for_getclassRecordingsBatchID(Integer expectedStatusCode) {

		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid Batchid for getclassRecordingsBatchID")
	public void admin_sends_https_request_with_invalid_batchid_for_getclass_recordings_batch_id() {

		response = getRequest.buildRequest("/batchRecordings/100000001", "get", "Bearer");

	}

	@Then("Admin receives {int} Not Found Status with message visible for getclassRecordingsBatchID")
	public void admin_receives_not_found_status_with_message_visible_for_getclass_recordings_batch_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid endpoint for getclassRecordingsBatchID")
	public void admin_sends_https_request_with_invalid_endpoint_for_getclass_recordings_batch_id() {

		response = getRequest.buildRequest("/batchRecordings/invalid", "get", "Bearer");

	}

	@When("Admin sends HTTPS Request with invalid method for getclassRecordingsBatchID")
	public void admin_sends_https_request_with_invalid_method_for_getclass_recordings_batch_id() {
		String bid = "/batchRecordings/" + BatchId + "";
		response = getRequest.buildRequest(bid, "post", "Bearer");

	}

	@Then("Admin receives {int} Method not allowed status visible for getclassRecordingsBatchID")
	public void admin_receives_method_not_allowed_status_visible_for_getclass_recordings_batch_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request without authorization for getclassRecordingsBatchID")
	public void admin_sends_https_request_without_authorization_for_getclass_recordings_batch_id() {
		String bid = "/batchRecordings/" + BatchId + "";
		response = getRequest.buildRequest(bid, "get", "noauth");

	}

	@Then("Admin receives {int} status with error message Unauthorized for getclassRecordingsBatchID")
	public void admin_receives_status_with_error_message_unauthorized_for_getclass_recordings_batch_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	// GetClassbyclassId

	@When("Admin sends HTTPS Request with endpoint for GetClassbyclassId")
	public void admin_sends_https_request_with_endpoint_for_get_classbyclass_id() {
		String cid = "/class/" + ClassId + "";
		response = getRequest.buildRequest(cid, "get", "Bearer");
	}

	@Then("Admin receives {int} OK Status with response body for GetClassbyclassId")
	public void admin_receives_ok_status_with_response_body_for_get_classbyclass_id(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid classId for GetClassbyclassId")
	public void admin_sends_https_request_with_invalid_class_id_for_get_classbyclass_id() {
		response = getRequest.buildRequest("/class/1000000011", "get", "Bearer");

	}

	@Then("Admin receives {int} Not Found Status with message visible for GetClassbyclassId")
	public void admin_receives_not_found_status_with_message_visible_for_get_classbyclass_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid endpoint for GetClassbyclassId")
	public void admin_sends_https_request_with_invalid_endpoint_for_get_classbyclass_id() {
		response = getRequest.buildRequest("/class/invalid", "get", "Bearer");

	}

	@When("Admin sends HTTPS Request with invalid method for GetClassbyclassId")
	public void admin_sends_https_request_with_invalid_method_for_get_classbyclass_id() {
		String cid = "/class/" + ClassId + "";
		response = getRequest.buildRequest(cid, "post", "Bearer");
	}

	@Then("Admin receives {int} Method not allowed status visible for GetClassbyclassId")
	public void admin_receives_method_not_allowed_status_visible_for_get_classbyclass_id(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request without authorization for GetClassbyclassId")
	public void admin_sends_https_request_without_authorization_for_get_classbyclass_id() {
		String cid = "/class/" + ClassId + "";
		response = getRequest.buildRequest(cid, "get", "noauth");
	}

	@Then("Admin receives {int} status with error message Unauthorized for GetClassbyclassId")
	public void admin_receives_status_with_error_message_unauthorized_for_get_classbyclass_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	// GetClassbyClasstopic

	@When("Admin sends HTTPS Request with endpoint for GetClassbyClasstopic")
	public void admin_sends_https_request_with_endpoint_for_get_classby_classtopic() {
		String ctopic = "/classes/" + ClassTopic + "";
		response = getRequest.buildRequest(ctopic, "get", "Bearer");
	}

	@Then("Admin receives {int} OK Status with response body for GetClassbyClasstopic")
	public void admin_receives_ok_status_with_response_body_for_get_classby_classtopic(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid classtopic for GetClassbyClasstopic")
	public void admin_sends_https_request_with_invalid_classtopic_for_get_classby_classtopic() {
		response = getRequest.buildRequest("/classes/anusuya", "get", "Bearer");
	}

	@Then("Admin receives {int} Not Found Status with message visible for GetClassbyClasstopic")
	public void admin_receives_not_found_status_with_message_visible_for_get_classby_classtopic(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid endpoint for GetClassbyClasstopic")
	public void admin_sends_https_request_with_invalid_endpoint_for_get_classby_classtopic() {
		response = getRequest.buildRequest("/classes/invalid", "get", "Bearer");

	}

	@When("Admin sends HTTPS Request with invalid method for GetClassbyClasstopic")
	public void admin_sends_https_request_with_invalid_method_for_getClassbyClasstopic() {
		String ctopic = "/classes/" + ClassTopic + "";
		response = getRequest.buildRequest(ctopic, "post", "Bearer");
	}

	@Then("Admin receives {int} Method not allowed status visible for GetClassbyClasstopic")
	public void admin_receives_method_not_allowed_status_visible_for_getClassbyClasstopic(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request without authorization for GetClassbyClasstopic")
	public void admin_sends_https_request_without_authorization_for_get_classby_classtopic() {
		String ctopic = "/classes/" + ClassTopic + "";
		response = getRequest.buildRequest(ctopic, "get", "noauth");
	}

	@Then("Admin receives {int} status with error message Unauthorized for GetClassbyClasstopic")
	public void admin_receives_status_with_error_message_unauthorized_for_get_classby_classtopic(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	// GetallClassesbyBatchId

	@When("Admin sends HTTPS Request with endpoint for GetallClassesbyBatchId")
	public void admin_sends_https_request_with_endpoint_for_getall_classesby_batch_id() {
		String btid = "/classesbyBatch/" + BatchId + "";
		response = getRequest.buildRequest(btid, "get", "Bearer");
	}

	@Then("Admin receives {int} OK Status with response body for GetallClassesbyBatchId")
	public void admin_receives_ok_status_with_response_body_for_getall_classesby_batch_id(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid Batchid for GetallClassesbyBatchId")
	public void admin_sends_https_request_with_invalid_batchid_for_getall_classesby_batch_id() {

		response = getRequest.buildRequest("/classesbyBatch/100000001", "get", "Bearer");
	}

	@Then("Admin receives {int} Not Found Status with message visible for GetallClassesbyBatchId")
	public void admin_receives_not_found_status_with_message_visible_for_getall_classesby_batch_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid endpoint for GetallClassesbyBatchId")
	public void admin_sends_https_request_with_invalid_endpoint_for_getall_classesby_batch_id() {
		response = getRequest.buildRequest("/classesbyBatch/invalid", "get", "Bearer");

	}

	@When("Admin sends HTTPS Request with invalid method for GetallClassesbyBatchId")
	public void admin_sends_https_request_with_invalid_method_for_getall_classesby_batch_id() {
		String btid = "/classesbyBatch/" + BatchId + "";
		response = getRequest.buildRequest(btid, "post", "Bearer");
	}

	@Then("Admin receives {int} Method not allowed status visible for GetallClassesbyBatchId")
	public void admin_receives_method_not_allowed_status_visible_for_getall_classesby_batch_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request without authorization for GetallClassesbyBatchId")
	public void admin_sends_https_request_without_authorization_for_getall_classesby_batch_id() {
		String btid = "/classesbyBatch/" + BatchId + "";
		response = getRequest.buildRequest(btid, "get", "noauth");
	}

	@Then("Admin receives {int} status with error message Unauthorized for GetallClassesbyBatchId")
	public void admin_receives_status_with_error_message_unauthorized_for_getall_classesby_batch_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	// GetallClassesbyStaffId

	@When("Admin sends HTTPS Request with endpoint for GetallClassesbyStaffId")
	public void admin_sends_https_request_with_endpoint_for_getall_classesby_staff_id() {
		String sid = "/classesByStaff/" + classStaffId + "";
		response = getRequest.buildRequest(sid, "get", "Bearer");
	}

	@Then("Admin receives {int} OK Status with response body for GetallClassesbyStaffId")
	public void admin_receives_ok_status_with_response_body_for_getall_classesby_staff_id(Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid StaffId for GetallClassesbyStaffId")
	public void admin_sends_https_request_with_invalid_StaffId_for_getall_classesby_staff_id() {
		response = getRequest.buildRequest("/classesByStaff/anu22", "get", "Bearer");

	}

	@Then("Admin receives {int} Not Found Status with message visible for GetallClassesbyStaffId")
	public void admin_receives_not_found_status_with_message_visible_for_getall_classesby_staff_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request with invalid endpoint for GetallClassesbyStaffId")
	public void admin_sends_https_request_with_invalid_endpoint_for_getall_classesby_staff_id() {
		response = getRequest.buildRequest("/classesByStaff/invalid", "get", "Bearer");
	}

	@When("Admin sends HTTPS Request with invalid method for GetallClassesbyStaffId")
	public void admin_sends_https_request_with_invalid_method_for_getall_classesby_staff_id() {
		String sid = "/classesByStaff/" + classStaffId + "";
		response = getRequest.buildRequest(sid, "post", "Bearer");
	}

	@Then("Admin receives {int} Method not allowed status visible for GetallClassesbyStaffId")
	public void admin_receives_method_not_allowed_status_visible_for_getall_classesby_staff_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

	@When("Admin sends HTTPS Request without authorization for GetallClassesbyStaffId")
	public void admin_sends_https_request_without_authorization_for_getall_classesby_staff_id() {
		String sid = "/classesByStaff/" + classStaffId + "";
		response = getRequest.buildRequest(sid, "get", "noauth");
	}

	@Then("Admin receives {int} status with error message Unauthorized for GetallClassesbyStaffId")
	public void admin_receives_status_with_error_message_unauthorized_for_getall_classesby_staff_id(
			Integer expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}
	
	
	// method to add to getClass Request - to read the validation for get all types directly pases and not fromn excel
	public Response buildRequest(String endpoint, String methodType, String tokenType) {
		RestAssured.baseURI = CommonUtils.baseURI;

		RequestSpecification request = RestAssured.given().baseUri(CommonUtils.baseURI)
				.header("Content-Type", "application/json").log().all(); // Logs request details

		// Modify the request based on token type
		switch (tokenType.toLowerCase()) {
		case "bearer":
			request.header("Authorization", "Bearer " + token()); // Add Bearer token
			break;

		case "noauth":
			// Do nothing; no Authorization header for noauth
			break;

		default:
			throw new IllegalArgumentException("Invalid token type: " + tokenType);
		}

		switch (methodType.toUpperCase()) {
		case "GET":
			return request.when().get(endpoint).then().extract().response();
		case "POST":
			return request.when().post(endpoint).then().extract().response();
		case "PUT":
			return request.when().put(endpoint).then().extract().response();
		case "DELETE":
			return request.when().delete(endpoint).then().extract().response();
		default:
			throw new IllegalArgumentException("Invalid HTTP method type: " + methodType);
		}

	}

}
*/