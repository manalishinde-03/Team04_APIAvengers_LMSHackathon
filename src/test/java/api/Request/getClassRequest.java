package api.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.Pojo.GetAllClassPojo;
import api.Pojo.classCrequestPojo;
import api.Pojo.createClassRequestBodyPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class getClassRequest extends CommonUtils{

	private RequestSpecification request;
	public Response response;
	GetAllClassPojo getRequest;
	int statusCode;
	String statusLine;
	
	

//	public String token() {
//
//		RestAssured.baseURI = CommonUtils.baseURI;
//		String requestBody = "{\n" + "\"userLoginEmailId\": \"sdet@gmail.com\",\n"
//				+ "\"password\":\"LmsHackathonApi@2024\"}\n" + "";
//
//		Response res = RestAssured.given().baseUri(CommonUtils.baseURI).header("Content-Type", "application/json")
//				.body(requestBody).when().post("/login").then().assertThat().extract().response();
//
//		System.out.println(res.asString());
//		JsonPath js = res.jsonPath();
//		String authtoken = js.getString("token");
//		return authtoken;
//
//	}
	
	
	//Code to getAllClass -data from Excel
	
	public RequestSpecification buildGetallBatchrequest(String GetTestCases) throws IOException
	{		
		getRequest = ExcelReader.getAllClassPojoData("class", GetTestCases);	
		System.out.println("buildGetallBatchrequest :::: getRequest ::"+getRequest.toString() );
		return request;
		
		
	}

	public Response getAllClassSendRequest() {
		 
		request = RestAssured.given().baseUri(baseURI);
		switch (ExcelReader.getauthorization) {
		case "bearer":
		//	request.header("Authorization", "Bearer " + token()); // Add Bearer token
			request.header("Authorization", "Bearer " + CommonUtils.getAdminToken()); // Add Bearer token

			break;

		case "noauth":
			// Do nothing; no Authorization header for noauth
			break;

		default:
		//	throw new IllegalArgumentException("Invalid token type: " + token());
			throw new IllegalArgumentException("Invalid token type: " + CommonUtils.getAdminToken());

		}	
		
		switch (ExcelReader.getmethod.toUpperCase()) {
		case "GET":
			response = request.when().get(ExcelReader.getendpoint);
			break;
		case "POST":
			response = request.when().post(ExcelReader.getendpoint);
			break;
		case "PUT":
			response = request.when().put(ExcelReader.getendpoint);
			break;
		case "DELETE":
			response = request.when().delete(ExcelReader.getendpoint);
			break;
		default:
			throw new IllegalArgumentException("Invalid HTTP method type: " +ExcelReader.getmethod);
			
		}
		
		statusCode = response.getStatusCode();
		statusLine = response.getStatusLine();

		System.out.println("Response status code:" + response.getStatusCode());
		System.out.println("ResponseBody:" + response.asString());

		return response;
	}
	
	
	public boolean StatuscodeValidation(String ExpStatusCode) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));
		return true;
	}
	
	
	
	

}
