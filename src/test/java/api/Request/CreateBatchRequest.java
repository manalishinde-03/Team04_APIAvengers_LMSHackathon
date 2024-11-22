package api.Request;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Pojo.BatchRequestPojo;
import api.Pojo.GetAllBatchPojo;
import api.Pojo.BatchRequestBodyPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBatchRequest extends CommonUtils{	
	int statusCode; 
	String statusLine;
	public Response response;
	private RequestSpecification request;	
	ObjectMapper objectMapper = new ObjectMapper();
	public String dataRequestBody;
	BatchRequestPojo createRequest;
	
	
	
	
	  public RequestSpecification createBatchbuildRequest(String Testcase) throws
	  JsonProcessingException, IOException{	  
		setProgramName(config.getString("ProgramName"));
		setProgramId(Integer.parseInt(config.getString("ProgramId")));
	  createRequest = batchPojoUtil("batch",Testcase); 
	  System.out.println("createBatchbuildRequest :::::: createRequest ::"+createRequest.toString());
	  dataRequestBody =	  objectMapper.writeValueAsString(createRequest.getCreatebatch());
	  System.out.println(dataRequestBody); return request; 
	  
	  }
	


public Response CreateBatchSendRequest(){
	  
      
	String Endpoint= (!createRequest.getEndpoint().equalsIgnoreCase("Valid")) ? createRequest.getEndpoint() :config.getString("createBatchEndpoint");
	String Authorization= (!createRequest.getAuthorizarion().equalsIgnoreCase("Valid")) ? null : getAdminToken();	
	
	
	request = RestAssured.given()
		    .baseUri(baseURI)                           
		    .header("Content-Type", "application/json")		    
		    .body(dataRequestBody) ;	
	
	  if (null!=Authorization) { 
		  request.header("Authorization", "Bearer " +	 Authorization );
	   }	
		
	response=request
		    .when()                                      
		    .post(Endpoint); 	
	
	statusCode = response.getStatusCode();
	statusLine = response.getStatusLine();
	
	System.out.println("Response status code:" +response.getStatusCode());
	System.out.println("ResponseBody:" + response.asString() );
	
	return response;
}


public boolean StatuscodeValidation(String ExpStatusCode)
{
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));
	return true ;
}

public boolean SchemaValidation()
{
	Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateBatchSchema.json")), true);
	
	return true;
}


public boolean contentTypeValidation()
{
	return true;
}

public boolean DataTypeValidation()
{
	return true;
}


public RequestSpecification buildGetallBatchrequest(String GetTestCases) throws IOException
{		
	
	createRequest = batchPojoUtil("Batch",GetTestCases);
	System.out.println("buildGetallBatchrequest :::: createRequest ::"+createRequest.toString() );
	return request;
}

public Response getAllbatchSendrequest()
{
	String Endpoint= (!createRequest.getEndpoint().equalsIgnoreCase("Valid")) ? createRequest.getEndpoint() :config.getString("createBatchEndpoint");
	String Authorization= (!createRequest.getAuthorizarion().equalsIgnoreCase("Valid")) ? null : getAdminToken();
	
	System.out.println("Sheet Endpoint"+createRequest.getEndpoint() +", Authorization from Sheet ::"+Authorization);
	
	request = RestAssured.given()
		    .baseUri(baseURI) ;                  
		    
	  if (null!=Authorization) { 
		  request.header("Authorization", "Bearer " +	 Authorization );
	   }	
		
	response=request
		    .when()                                      
		    .get(Endpoint); 	
	
	statusCode = response.getStatusCode();
	statusLine = response.getStatusLine();
	
	System.out.println("Response status code:" +response.getStatusCode());
	System.out.println("ResponseBody:" + response.asString() );	
	return response;
	
	
}




public GetAllBatchPojo getAllbatchData(String Sheet, String TestCase) throws IOException
{
	XSSFWorkbook wb;		
	File file = new File(config.getString("DataFile"));																							
	FileInputStream fis = new FileInputStream(file); 
	wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.getSheet(Sheet);	
	
	GetAllBatchPojo getall = new GetAllBatchPojo();
	for (Row row : sheet) {

		Cell firstCell = row.getCell(0);
		if (firstCell != null && firstCell.getCellType() == CellType.STRING
				&& firstCell.getStringCellValue().equals(TestCase)) 
		{	
			getall.setEndpoint(row.getCell(1).getStringCellValue());
			getall.setAuthorizarion(row.getCell(2).getStringCellValue());
		}
		wb.close();	
	}
	return getall;
}



public  BatchRequestPojo batchPojoUtil(String Sheet, String TestCase) throws IOException {
	
	XSSFWorkbook wb;		
	File file = new File(config.getString("DataFile"));																							
	FileInputStream fis = new FileInputStream(file); 
	wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.getSheet(Sheet);		
	BatchRequestPojo batchreq= new BatchRequestPojo();
	BatchRequestBodyPojo createBatch= new BatchRequestBodyPojo();	
	for (Row row : sheet) {

		Cell firstCell = row.getCell(0);
		if(null != firstCell && firstCell.getStringCellValue()!="" && firstCell.getCellType() == CellType.STRING
				&& firstCell.getStringCellValue().equals(TestCase) ) {
			
			 System.out.println("Row :"+firstCell.getStringCellValue());
			 
			 
			if (TestCase.startsWith("POST")) 
			{	
				
				Cell BatchDescription=row.getCell(5);
				if (BatchDescription != null && BatchDescription.getCellType() == CellType.STRING) {
		            createBatch.setBatchDescription(BatchDescription.getStringCellValue());
		        } else {
		            createBatch.setBatchDescription(null); // Set to null if the cell is empty or doesn't exist
		        }
				
				
				
				Cell Batchname = row.getCell(4);
				
				if (Batchname != null && Batchname.getCellType() == CellType.STRING) {
		            createBatch.setBatchName(Batchname.getStringCellValue());
		        } else {
		            createBatch.setBatchName(null); // Set to null if the cell is empty or doesn't exist
		        }
				//createBatch.setBatchName(row.getCell(4).getStringCellValue());			
				
				
				String prgname= row.getCell(9).getStringCellValue();
				if(prgname=="Valid")
				{
					createBatch.setProgramName(getProgramName());
				}else {
					createBatch.setProgramName(row.getCell(9).getStringCellValue());
					
				}
				
				
				Cell BatchStatus = row.getCell(7);
		        if (BatchStatus != null && BatchStatus.getCellType() == CellType.STRING) {
		            createBatch.setBatchStatus(BatchStatus.getStringCellValue());
		        } else {
		            createBatch.setBatchStatus(null); // Set to null if the cell is empty or doesn't exist
		        }
				
		        Cell prgID=row.getCell(8);
				if (prgID != null && prgID.getStringCellValue().equalsIgnoreCase("Valid")){
					createBatch.setProgramId(getProgramId());   
		        }
				else if (prgID != null){						   
			            createBatch.setProgramId(Integer.parseInt(prgID.getStringCellValue()));
			        }else {
			        	createBatch.setProgramId(null);
			        }
				
				
				if (row.getCell(6) != null) {
		            String NoOfClasses = row.getCell(6).getStringCellValue();		            
		            createBatch.setBatchNoOfClasses(NoOfClasses != null && !NoOfClasses.isEmpty() ? Integer.parseInt(NoOfClasses.trim()) : null);
		        }         
				batchreq.setCreatebatch(createBatch); 
					
				}

				//System.out.println("Endpoint ::" +row.getCell(2).getStringCellValue());
				batchreq.setEndpoint(row.getCell(2).getStringCellValue());		
				
				//System.out.println("Authorizrion ::" +row.getCell(10).getStringCellValue());
				batchreq.setAuthorizarion(row.getCell(10).getStringCellValue());
			}	
		
		}		
	
	wb.close();			
	
	return batchreq;
	
}




}
