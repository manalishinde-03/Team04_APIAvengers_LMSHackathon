package DataDriven;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Pojo.LmsInfo;
import api.Utility.ExcelUtils;
import io.restassured.response.Response;

public class DataDrivenTesting 
{
	
    private static Response response;
    private static final String BASE_URL = "https://lms-hackthon-oct24-3efc7e0df381.herokuapp.com/lms";
    
@Test
public void post() throws JsonParseException,JsonMappingException, IOException
{

String excelPath ="./TestData/testData.xlsx";
String sheetName ="Class";

ExcelUtils excel = new ExcelUtils(excelPath,sheetName);

//Create a JSON object for request and add the data from excel to it,

JSONObject request = new JSONObject();
request.put("Batchname",excel.getCellData(1,0));
request.put("Staffname",excel.getCellData(1,1));
request.put("Classno",excel.getCellData(1,2));
request.put("Classtopic",excel.getCellData(1,3));
request.put("Classdate",excel.getCellData(1,4));
request.put("Status",excel.getCellData(1,5));

//Now we are going to make a request by having the request in the body and response


response = given().auth().basic(UserLoginEmailId,password).header("Content-type","application/json").and().body(request).
when().post(BASE_URL + "/saveprogram").then().extract().response();
//Get the response as string and store it in string variable
String stringToParse = response.getBody().asString();
//Using ObjectMapper, we are going to use readValue Method to deserialize JSON conte content String as below,
ObjectMapper objectMapper = new ObjectMapper();
LmsInfo lmsInfo = objectMapper.readValue(stringToParse, LmsInfo.class);
writeResponse(lmsInfo);

}

private Object given() {
	// TODO Auto-generated method stub
	return null;
}

private void writeResponse(LmsInfo lmsInfo) 
{
XSSFWorkbook workbook = new XSSFWorkbook();
XSSFSheet sheet = workbook.createSheet("Sheet1");
Object[][] respData = {
		{"BatchName ", "StaffName", "ClassNo", "ClassTopic","ClassDate","Status"},
		{lmsInfo.getBName(), lmsInfo.getSName(), lmsInfo.getCno(),lmsInfo.getCtopic(),lmsInfo.getCdate(),lmsInfo.getStatus()
			
		}};
int rowCount = 0;

for (Object[] rData  : respData)
{
  Row row = sheet.createRow(++rowCount); 
  int columnCount = 0; 
  for (Object field : rData) 
	  []
  Cell cell = row.createCell()++columnCount);
  if (field instanceof String) 
  {
  cell.setCellValue((String) field);
  }
  else if (field instanceof Integer)
  {
	    cell.setCellValue((Integer) field);
  }
  
}
}
try 
{

FileOutputStream outputStream = new FileOutputStream("./data/Response.xlsx");
workbook.write(outputStream);
workbook.close();
}
catch(IOException e)
{
e.printStackTrace();
｝

}
  
