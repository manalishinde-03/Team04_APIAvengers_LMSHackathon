package api.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import api.Pojo.GetAllClassPojo;
import api.Pojo.classCrequestPojo;
import api.Pojo.createClassRequestBodyPojo;
import api.StepDefinitions.ProgramCreateSteps;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import api.Utility.LoggerLoad;

public class createClassRequest extends CommonUtils {

	int statusCode;
	String statusLine;
	public Response response;
	private RequestSpecification request;
	ObjectMapper objectMapper = new ObjectMapper();
	public String dataRequestBody;
	classCrequestPojo createRequest;
	private static final Logger log = LogManager.getLogger(createClassRequest.class);

	

	public RequestSpecification createClassbuildRequest(String Testcase) throws JsonProcessingException, IOException {

		//driving batch ID from previous batch test
		createRequest = ExcelReader.classPojoUtil("class", Testcase);
		dataRequestBody = objectMapper.writeValueAsString(createRequest.getClass());
		System.out.println("createClassbuildRequest :::::: createRequest ::" + createRequest.toString());
		dataRequestBody = objectMapper.writeValueAsString(createRequest.getCreateClass());
		System.out.println(dataRequestBody);
		return request;

	}

	//  Create Class Request

	public Response CreateClassSendRequest() {


		request = RestAssured.given().baseUri(baseURI).header("Content-Type", "application/json").body(dataRequestBody);

		if (ExcelReader.baseUrlFromDataFile != null && ExcelReader.baseUrlFromDataFile.contains("InvalidUri")) {
		  	request.baseUri("https://lms-hackthon-oct24-3efc7e0df381.herokuapp.com/lmsInvalid");
        }
		
		switch (ExcelReader.authorization) {
		case "bearer":
			request.header("Authorization", "Bearer " + CommonUtils.getAdminToken()); // Add Bearer token

			break;

		case "noauth":
			break;

		default:
			throw new IllegalArgumentException("Invalid token type: " + CommonUtils.getAdminToken());
		}

		response = request.when().post(ExcelReader.endpoint);

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
		
	public boolean SchemaValidation() {

		File schemaFile = new File("src/test/resources/schemas/CreateClassSchema.json");
		if (!schemaFile.exists()) {
			throw new IllegalArgumentException("JSON schema file not found: " + schemaFile.getPath());
		}

		Assert.assertNotNull(response.then().body(JsonSchemaValidator.matchesJsonSchema(schemaFile)));

		return true;
	}

	public boolean contentTypeValidation() {
		return true;
	}

	public boolean DataTypeValidation() {
		return true;
	}

	

	
}
