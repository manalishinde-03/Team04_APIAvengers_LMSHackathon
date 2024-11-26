package api.StepDefinitions;

import java.io.IOException;
import org.testng.Assert;

import api.Request.GetClassRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetClassSteps
{

	GetClassRequest getRequest = new GetClassRequest();
	private RequestSpecification request;
	private Response response;
	private String ClassId = "108";
	private String classStaffId = "U49";

	@Given("Admin creates the GET Request")
	public void admin_creates_the_get_request() throws IOException
	{
		RestAssured.baseURI = CommonUtils.baseURI;
	}
	
    @When("Admin sends HTTPS Request with the endpoint")
    public void admin_sends_https_request_with_the_endpoint()
	{
		response = getRequest.buildRequest("/classrecordings", "get", "Bearer");
    }

@Then("Admin receives {int} OK Status with response body")
public void admin_receives_ok_status_with_response_body(Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
}

@When("Admin sends HTTPS Request with invalid endpoint")
public void admin_sends_https_request_with_invalid_endpoint()
{
	response = getRequest.buildRequest("/classrecording", "get", "Bearer");
}

@Then("Admin receives {int} Not Found with message visible")
public void admin_receives_not_found_with_message_visible(Integer expectedStatusCode)
{
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode); 
	
}
@When("Admin sends HTTPS Request with invalid method for getallrecordings")
public void admin_sends_https_request_with_invalid_method_for_getallrecordings() {
	response = getRequest.buildRequest("/classrecordings", "post", "Bearer");

}
@Then("Admin receives {int} and Method not allowed status visible")
public void admin_receives_method_not_allowed_status_visible_for_getallrecordings(Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
}
@When("Admin sends HTTPS Request without authorization for getallrecordings")
public void admin_sends_https_request_without_authorization_for_get_allrecordings() {

	response = getRequest.buildRequest("/classrecordings", "get", "noauth");

}
@Then("Admin receives {int} status with error message Unauthorized")
public void admin_receives_status_with_error_message_unauthorized(Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
}

//Get Class Recordings by Class Id

@When("Admin sends HTTPS Request with endpoint for getclassRecordingsClassID")
public void admin_sends_https_request_with_endpoint_for_getclass_recordings_class_id() {
	String cid = "/classRecordings/" + ClassId + "";
	response = getRequest.buildRequest(cid, "get", "Bearer");
}

@Then("Admin receives {int} OK Status with response body for getclassRecordingsClassID")
public void admin_receives_ok_status_with_response_body_for_getclassRecordingsClassID(Integer expectedStatusCode) {

	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}
@When("Admin sends HTTPS Request with invalid Classid for getclassRecordingsClassID")
public void admin_sends_https_request_with_invalid_classid_for_getclass_recordings_class_id() {

	response = getRequest.buildRequest("/classRecordings/1000", "get", "Bearer");			

}
@Then("Admin receives {int} Not Found Status with message visible")
public void admin_receives_not_found_status_with_message_visible(Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);   
}
@When("Admin sends HTTPS Request with invalid endpoint for getclassRecordingsClassID")
public void admin_sends_https_request_with_invalid_endpoint_for_getclass_recordings_class_id() {

	response = getRequest.buildRequest("/classRecordings/invalid", "get", "Bearer");

}
@Then("Admin receives {int} Not Found Status with message visible for GetClassRecordingsbyclassId")
public void admin_receives_not_found_status_with_message_visible_for_get_class_recordings_by_class_id(
		Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}
@When("Admin sends HTTPS Request with invalid method for getclassRecordingsClassID")
public void admin_sends_https_request_with_invalid_method_for_getclass_recordings_class_id() {
	String cid = "/classRecordings/" + ClassId + "";
	response = getRequest.buildRequest(cid, "post", "Bearer");

}

@Then("Admin receives {int} Method not allowed status visible for getclassRecordingsClassID")
public void admin_receives_method_not_allowed_status_visible_for_getclass_recordings_class_id(
		Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}

@When("Admin sends HTTPS Request without authorization for getclassRecordingsClassID")
public void admin_sends_https_request_without_authorization_for_getclass_recordings_class_id() {
	String cid = "/classRecordings/" + ClassId + "";
	response = getRequest.buildRequest(cid, "get", "noauth");

}

@Then("Admin receives {int} status with error message Unauthorized for getclassRecordingsClassID")
public void admin_receives_status_with_error_message_unauthorized_for_getclass_recordings_class_id(
		Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}

//Get Class Recordings for upcoming classes for Student Id

@When("Admin sends HTTPS Request for upcoming classes with valid Student Id")
public void admin_sends_https_request_for_upcoming_classes_with_valid_student_id() {
	String stid = "/upcomingClasses/" + classStaffId + ""; 
	response = getRequest.buildRequest(stid, "get", "Bearer");
}

@Then("Admin receives {int} OK Status with response body for upcoming classes with valid Student Id")
public void admin_receives_ok_status_with_response_body_for_upcoming_classes_with_valid_student_id(Integer expectedStatusCode) {

	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}

 @When("Admin sends HTTPS Request with invalid Student Id for upcoming classes")
 public void admin_sends_https_request_with_invalid_student_id_for_upcoming_classes() {
	response = getRequest.buildRequest("/upcomingClasses/100000001", "get", "Bearer");

}
@Then("Admin receives {int} Not Found Status with message visible for upcoming classes with invalid Student Id")
public void admin_receives_not_found_status_with_message_visible_for_upcoming_classes_with_invalid_student_id(
		Integer expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}


@When("Admin sends HTTPS Request with invalid endpoint for upcoming classes")
public void admin_sends_https_request_with_invalid_endpoint_for_upcoming_classes() {

	response = getRequest.buildRequest("/upcomingClassesabc/", "get", "Bearer");

}
 @Then("Admin receives {int} Not Found Status with message visible for upcoming classes")
public void admin_receives_not_found_status_with_message_visible_for_upcoming_classes
		(Integer expectedStatusCode) 
 {
	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

}

 @When("Admin sends HTTPS Request with invalid method for upcoming classes")
 public void admin_sends_https_request_with_invalid_method_for_upcoming_classes() {
 	String stuid = "/upcomingClasses/" + classStaffId + "";
 	response = getRequest.buildRequest(stuid, "post", "Bearer");

 }

 @Then("Admin receives {int} and Method not allowed status visible for upcoming classes")
 public void admin_receives_and_method_not_allowed_status_visible_for_upcoming_classes(
 		Integer expectedStatusCode) {
 	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

 }

 @When("Admin sends HTTPS Request without authorization for upcoming classes")
 public void admin_sends_https_request_without_authorization_for_upcoming_classes() {
 	String stuid = "/upcomingClasses/" + classStaffId + "";
 	response = getRequest.buildRequest(stuid, "get", "noauth");

 }

 @Then("Admin receives {int} status with error message Unauthorized for upcoming classes")
 public void admin_receives_status_with_error_message_unauthorized_for_upcoming_classes(
 		Integer expectedStatusCode) {
 	Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
}
}



	





