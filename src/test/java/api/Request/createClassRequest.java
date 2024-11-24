package api.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.surefire.shared.lang3.StringUtils;
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

import api.pojo.classCrequestPojo;
import api.pojo.createClassRequestBodyPojo;
import api.utility.CommonUtils;

public class createClassRequest extends CommonUtils {

	int statusCode;
	String statusLine;
	public Response response;
	private RequestSpecification request;
	ObjectMapper objectMapper = new ObjectMapper();
	public String dataRequestBody;
	classCrequestPojo createRequest;
	String endpoint;
	String authorization;

	public String token() {

		RestAssured.baseURI = CommonUtils.baseURI;
		String requestBody = "{\n" + "\"userLoginEmailId\": \"sdet@gmail.com\",\n"
				+ "\"password\":\"LmsHackathonApi@2024\"}\n" + "";

		Response res = RestAssured.given().baseUri(CommonUtils.baseURI).header("Content-Type", "application/json")
				.body(requestBody).when().post("/login").then().assertThat().extract().response();

		System.out.println(res.asString());
		JsonPath js = res.jsonPath();
		String authtoken = js.getString("token");
		return authtoken;
	}

	public RequestSpecification createClassbuildRequest(String Testcase) throws JsonProcessingException, IOException {

		setBatchName(config.getString("BatchName"));
		setBatchID(Integer.parseInt(config.getString("BatchId")));

		// RestAssured.baseURI = CommonUtils.baseURI;
		createRequest = classPojoUtil("class", Testcase);
		dataRequestBody = objectMapper.writeValueAsString(createRequest.getClass());
		System.out.println("createClassbuildRequest :::::: createRequest ::" + createRequest.toString());
		dataRequestBody = objectMapper.writeValueAsString(createRequest.getCreateClass());
		System.out.println(dataRequestBody);
		return request;

	}

	public Response CreateClassSendRequest() {

		// String Endpoint= CommonUtils.createClassEndPoint;
		// String Authorization= token();

		String Authorization = token();

		request = RestAssured.given().baseUri(baseURI).header("Content-Type", "application/json").body(dataRequestBody);

		switch (authorization) {
		case "bearer":
			request.header("Authorization", "Bearer " + token()); // Add Bearer token
			break;

		case "noauth":
			// Do nothing; no Authorization header for noauth
			break;

		default:
			throw new IllegalArgumentException("Invalid token type: " + Authorization);
		}

//		if (null!=Authorization) {
//			request.header("Authorization", "Bearer " + Authorization );
//			}	 
//			
		response = request.when().post(endpoint);

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

	public boolean SchemaValidation() {

		File schemaFile = new File("src/test/resources/Schema/CreateClassSchema.json");
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

	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return ""; // Return empty string for null cells
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case BLANK:
			return "";
		default:
			return "Unsupported Type";
		}
	}

	public classCrequestPojo classPojoUtil(String Sheet, String TestCase) throws IOException {

		XSSFWorkbook wb;
		File file = new File(config.getString("DataFile"));
		FileInputStream fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(Sheet);
		classCrequestPojo classreq = new classCrequestPojo();
		createClassRequestBodyPojo createClass = new createClassRequestBodyPojo();
		// int BATCH_ID = 8570;

		for (Row row : sheet) {

			Cell firstCell = row.getCell(0);

			if (null != firstCell && firstCell.getStringCellValue() != "" && firstCell.getCellType() == CellType.STRING
					&& firstCell.getStringCellValue().equals(TestCase)) {

				System.out.println("Row :" + firstCell.getStringCellValue());

				if (TestCase.startsWith("POST"))

				{
					if (row.getCell(4) != null) {
						createClass.setBatchId(Integer.parseInt(getCellValue(row.getCell(4))));
					}

					createClass.setClassComments(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

					if (row.getCell(6) != null) {

						String classDate = row.getCell(6).getStringCellValue();
						System.out.println("Date class1: " + classDate);
						classDate = classDate.replaceAll("^\"|\"$", "");
						System.out.println("Date class2: " + classDate);

//		            String classDate1 = getCellValue(row.getCell(6));
//		            System.out.println("Date class1: "+classDate1);
						createClass.setClassDate(classDate);

					}

					createClass
							.setClassDescription(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : null);

					if (row.getCell(8) != null) {
						String classNo = getCellValue(row.getCell(8));
						System.out.println("Class No: " + classNo);
						createClass.setClassNo(
								classNo != null && !classNo.isEmpty() ? Integer.parseInt(classNo.trim()) : null);
					}

					createClass.setClassNotes(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null);
					createClass.setClassRecordingPath(
							row.getCell(10) != null ? row.getCell(10).getStringCellValue() : null);
					createClass.setClassStaffId(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : null);
					createClass.setClassTopic(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);

					// date1,date2
					if (row.getCell(13) != null) {
						String classScheduleListStr = row.getCell(13).getStringCellValue();
						List<String> list = Arrays.asList(classScheduleListStr.split(","));
						createClass.setClassScheduledDates(list);
					}

					classreq.setCreateClass(createClass);
					classreq.setAuthorization(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null);
					System.out.println("Authorization :" + row.getCell(15));
					authorization = classreq.getAuthorization();

					classreq.setEndpoint(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
					System.out.println("Endpoint" + row.getCell(2));
					endpoint = classreq.getEndpoint();
				}
			}
			wb.close();

		}
		return classreq;
	}
}
