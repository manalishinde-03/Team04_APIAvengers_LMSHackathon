package api.Request;

import api.Payload.CreateProgramPayload;
import api.Payload.UserLoginPayload;
import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
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
	
	public RequestSpecification buildRequest() {
		RestAssured.baseURI = baseURI;
		createProgramPojo=CreateProgramPayload.createProgram();
		
		request = RestAssured.given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Content-Type", "application/json")
				.body(createProgramPojo);
		
		System.out.println("POST Program Request :" +request.log().all());
		return request;
		
	}
	
	public RequestSpecification buildRequestWithMandatoryDetails() {
		RestAssured.baseURI = baseURI;
		createProgramPojo=CreateProgramPayload.createProgramWithMandatoryDetails();
		
		request = RestAssured.given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
				.header("Content-Type", "application/json")
				.body(createProgramPojo);
		
		System.out.println("POST Program Request :" +request.log().all());
		return request;
		
	}
	
	public RequestSpecification buildRequest(String user) {
		RestAssured.baseURI = baseURI;
		createProgramPojo=CreateProgramPayload.createProgram();
		switch (user) {
		
		case "Admin":
			request = RestAssured.given()
			.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
			.header("Content-Type", "application/json");
			break;
		case "NoAuth":
			request = RestAssured.given()
					.header("Authorization", "No Auth")
					.header("Content-Type", "application/json");
			break;
		default:
			throw new IllegalArgumentException("Unsupported user type: " + user);
		}
		return request;
	}
	
	public Response sendRequest() {
		
			response = request.when().post(createProgramEndPoint);
			
			statusCode = response.getStatusCode();
			statusLine = response.getStatusLine();
			
			programID = response.jsonPath().getInt("programId");
			programName = response.jsonPath().getString("programName");
			programStatus = response.jsonPath().getString("programStatus");
			
			System.out.println("Response :" +response.asPrettyString());
			System.out.println("status Code :" +statusCode);
			System.out.println("status Line :" +statusLine);
			programName = response.jsonPath().getString("programName");
			programStatus = response.jsonPath().getString("programStatus");
			
			CommonUtils.setProgramID(programID);
			CommonUtils.setProgramName(programName);
			CommonUtils.setProgramStatus(programStatus);
			
		return response;
	}
	
	public Response sendRequestWithNoAuth() {
		
		response = request.when().post(createProgramEndPoint);
		
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();
		
		
	return response;
}
	
	

}
