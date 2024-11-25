package api.Request;

import api.Payload.UserLoginPayload;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserLoginRequest extends CommonUtils{
	
	public static String adminToken;
	public Response response;
	private RequestSpecification request;
	int statusCode; 
	String statusLine;
	LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
	
	//Admin Valid login request
//	public Response adminLoginRequest() {
//		
//		loginRequestPojo=UserLoginPayload.adminLogin();
//		
//		 response = RestAssured.given()				 
//				.baseUri(baseURI)
//				.contentType(ContentType.JSON)
//				.body(loginRequestPojo)
//				.post(loginEndPoint);		
//		 
//		 System.out.println("Response: " + response.asPrettyString());
//		
//		 adminToken = response.jsonPath().getString("token");		
//		setAdminToken(adminToken);
//		statusCode = response.getStatusCode();
//		return response;
//	}
	
	public RequestSpecification buildRequest() {
		RestAssured.baseURI = baseURI;
		loginRequestPojo=UserLoginPayload.adminLogin();
		
		request = RestAssured.given()
				.baseUri(baseURI)
				.header("Content-Type", "application/json")
				.body(loginRequestPojo);
		
		System.out.println("Request :" +request.log().all());
		return request;
		
	}
	
	public Response sendRequest() {
		
			response = request.when().post(loginEndPoint);
			
			adminToken = response.jsonPath().getString("token");
			setAdminToken(adminToken);
			statusCode = response.getStatusCode();
			statusLine = response.getStatusLine();
			
			//System.out.println("Response :" +response.asPrettyString());
			System.out.println("Token :" +adminToken);			
		return response;
	}
	
	


}
