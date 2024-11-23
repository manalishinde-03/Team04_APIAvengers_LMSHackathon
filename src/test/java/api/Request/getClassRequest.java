package api.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class getClassRequest {

	private RequestSpecification request;
	public Response response;

	public String token() {

		RestAssured.baseURI = CommonUtils.baseURI;
		String requestBody = "{\n" + "\"userLoginEmailId\": \"sdet@gmail.com\",\n"
				+ "\"password\":\"LmsHackathonApi@2024\"}\n" + "";

		Response res = RestAssured.given().baseUri(CommonUtils.baseURI).header("Content-Type", "application/json")
				.body(requestBody).when().post("/login").then().assertThat().extract().response();

		System.out.println(res.asString());
		JsonPath js = res.jsonPath();
		String authtoken = js.getString("token");
		System.out.println(authtoken);

		return authtoken;

	}

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

//	private static final String FILE_PATH = "src/test/resources/TestData/testData1.xlsx";
//	public static Map<String, String> getTestData(String testCaseId) throws IOException {
//		FileInputStream fis = new FileInputStream(FILE_PATH);
//		Workbook workbook = new XSSFWorkbook(fis);
//		Sheet sheet = workbook.getSheet("Sheet2");
//
//		Map<String, String> testData = new HashMap<>();
//
//		for (Row row : sheet) {
//			Cell testCaseCell = row.getCell(0); // Assuming TestCaseID is in the first column
//			if (testCaseCell != null && testCaseCell.getStringCellValue().equals(testCaseId)) {
//				for (Cell cell : row) {
//					String header = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
//					String value = cell.getCellType() == CellType.NUMERIC
//							? String.valueOf((int) cell.getNumericCellValue())
//							: cell.getStringCellValue();
//					testData.put(header, value);
//				}
//				break;
//			}
//		}
//		workbook.close();
//		fis.close();
//		return testData;
//	} 

}
