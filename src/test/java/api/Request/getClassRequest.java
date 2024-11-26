package api.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import api.StepDefinitions.classGetAllSteps;
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
	 private static final Logger log = LogManager.getLogger(classGetAllSteps.class);
	
	
	//Code to getAllClass -data from Excel
	
	public RequestSpecification buildGetallBatchrequest(String GetTestCases) throws IOException
	{		
		getRequest = ExcelReader.getAllClassPojoData("class", GetTestCases);	
		log.info("buildGetallBatchrequest :::: getRequest ::"+getRequest.toString() );
		return request;
		
		
	}

	public Response getAllClassSendRequest() {
		 
		request = RestAssured.given().baseUri(baseURI);
		switch (ExcelReader.getauthorization) {
		case "bearer":
			request.header("Authorization", "Bearer " + CommonUtils.getAdminToken()); // Add Bearer token

			break;

		case "noauth":
			break;

		default:
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

		log.info("Response status code:" + response.getStatusCode());
		log.info("ResponseBody:" + response.asString());

		return response;
	}
	
	
	public boolean StatuscodeValidation(String ExpStatusCode) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));
		return true;
	}
	
	
	
	

}
