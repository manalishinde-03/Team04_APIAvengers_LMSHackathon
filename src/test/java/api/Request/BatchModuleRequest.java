package api.Request;


import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import api.Pojo.BatchRequestPojo;
import api.Pojo.BatchRequestBodyPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BatchModuleRequest extends CommonUtils {	
	
	
	int statusCode; 
	String statusLine;
	public Response response;
	private RequestSpecification request;	
	ExcelReader excelUtil = new ExcelReader();
	ObjectMapper objectMapper = new ObjectMapper();
	public String dataRequestBody;
	BatchRequestPojo createRequest;
	Logger logger = CommonUtils.getLogger();
	
	// Build Request for Create batch
	
	  public RequestSpecification createBatchbuildRequest(String Testcase) throws
	  JsonProcessingException, IOException{	  
		  
		  programIDArrList.add(Integer.parseInt(config.getString("ProgramId1")));
		  programNameArrList.add(config.getString("ProgramName1"));		  
		  programIDArrList.add(Integer.parseInt(config.getString("ProgramId2")));
		  programNameArrList.add(config.getString("ProgramName2"));	
		  
		createRequest= excelUtil.batchPojoUtil("Batch",Testcase);  
		
	   dataRequestBody =objectMapper.writeValueAsString(createRequest.getCreatebatch());
	   
	   logger.info("Post Request Body :: "+ dataRequestBody);   
	 
	   return request; 
	  
	  }
	
// Sending request for Create Batch Request

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
	logger.info("Response Code ::" + response.getStatusCode());
	logger.info("ResponseBody:" + response.asString());
	return response;
	
}

//Sending request for Update By Batch ID Request

public Response sendRequestupdateBybatchID()
{
	String UpdateBatchEndpoint = (!createRequest.getEndpoint().equalsIgnoreCase("Valid")) ? createRequest.getEndpoint(): config.getString("updateBatchbyID");
	String Authorization = (!createRequest.getAuthorizarion().equalsIgnoreCase("Valid")) ? null : getAdminToken();	
	Integer BatchID=(createRequest.getBatchId()==null? batchIDArrList.get(0) :createRequest.getBatchId());	
	
	System.out.println("UpdateBatchEndpoint::"+UpdateBatchEndpoint+BatchID);
	System.out.println("Authorization::"+Authorization);
	System.out.println("POJO::"+dataRequestBody);
	
	request =  RestAssured.given()
		    .baseUri(baseURI)                           
		    .header("Content-Type", "application/json")		    
		    .body(dataRequestBody) ;
			

	if (null != Authorization) {
		request.header("Authorization", "Bearer " + Authorization);
	}	
			
	System.out.println("Request ::" + request.toString());	
	
	response=request
		    .when()                                      
		    .put(UpdateBatchEndpoint + BatchID); 
	
	
	System.out.println("Response Code"+ response.getStatusCode());
	System.out.println("Response "+response.asPrettyString());
	
	return response;
	

}

// Update by BatchId Validation


public boolean updateBybatchIdValidation(String ExpCode)
{
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpCode));
	if(response.statusCode()==200) {
		response.then().assertThat().header("Content-Type", "application/json");
		createBatch_dataTypevalidation();		
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);	
		Integer BatchId1 = jsonPath.getInt("batchId");
		String BatchName1=jsonPath.getString("batchName");
		Assert.assertEquals(BatchId1,batchIDArrList.get(0));	
		Assert.assertEquals(BatchName1,batchNameArrList.get(0));		
		
	}
	return true;
}


//Build Update By Batch Id request

public RequestSpecification buildUpdateByBatchIDrequest(String UpdateByBatchIDTestcases) throws IOException
{
		createRequest = excelUtil.batchPojoUtil("Batch",UpdateByBatchIDTestcases);		

		dataRequestBody =objectMapper.writeValueAsString(createRequest.getCreatebatch());

		System.out.println("dataRequestBody ::"+dataRequestBody);

		return request;

}


public void createBatchResponseValidation(String expcode)
{
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(expcode));
	
	if(response.statusCode()==201) {
		
		response.then().assertThat().header("Content-Type", "application/json");
		createBatch_dataTypevalidation();		
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);	
		Integer BatchId1 = jsonPath.getInt("batchId");
		String BatchName1=jsonPath.getString("batchName");	
		
		batchIDArrList.add(BatchId1);
		batchNameArrList.add(BatchName1);
		
		logger.info("Batch ID added to the ArrayList --> " +batchIDArrList.toString());
		logger.info("Batch Name added to the ArrayList--> " +batchNameArrList.toString());
		
		//Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createBatchSchema.json")), true);
		
		
	}
	
}

// Get All batch Response Validation

public void GetAllBatchREsponseValidation(String ExpStatusCode)
{
	logger.info("Get All batches Response Status Code---->"+response.getStatusCode());
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));	
	if(response.statusCode()==200) {
		response.then().assertThat().header("Content-Type", "application/json");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAllbatchesSchema.json"));
	}
}


// Get batch By Program ID validation

public boolean getBatchByProgramIDvalidation(String StatusCode)
{
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(StatusCode));
	logger.info("Response Code----" + response.getStatusCode());
	
	if(response.getStatusCode()==200) {
		response.then().assertThat().header("Content-Type", "application/json");
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);	
		System.out.println("Response Body::"+ responseBody);
		System.out.println("ProgramIds List::"+ jsonPath.getInt("programId"));
			
	}
	return true;
}

//Get By Batch ID Response Body Validation

public boolean getBybatchIDValidation(String StatusCode)
{	
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(StatusCode));
	logger.info("Response Code" + response.getStatusCode());
	
	if(response.getStatusCode()==200) {
		response.then().assertThat().header("Content-Type", "application/json");
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);		
		Integer BatchId1 = jsonPath.getInt("batchId");		
		logger.info("Response Body Batch ID --->"+BatchId1);				
		Assert.assertEquals(BatchId1,batchIDArrList.get(1));
		
		//Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createBatchSchema.json")), true);
	}
	return true;
}


//Get By Batch Name Response Body Validation

public boolean getByBatchName_MessageValidation(String ExpCode)
{
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpCode));
	logger.info("Response Code" + response.getStatusCode());
	
	if(response.getStatusCode()==200) {
		response.then().assertThat().header("Content-Type", "application/json");
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);		
		String BatchName1=jsonPath.getString("[0].batchName");	
	
		logger.info("Response Body Batch Name --->"+BatchName1);
		Assert.assertEquals(BatchName1,batchNameArrList.get(0));
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAllbatchesSchema.json"));
				
	}	
		return true;
}


// Create Batch Data Type Validation

public void createBatch_dataTypevalidation()
{
	String batchname = response.jsonPath().getString("batchName");
    String BatchDescription = response.jsonPath().getString("batchDescription");
    String BatchStatus = response.jsonPath().getString("batchStatus");
    String ProgramName = response.jsonPath().getString("programName");    
    Integer BatchID = response.jsonPath().getInt("batchId");
    Integer ProgramID = response.jsonPath().getInt("programId");  
    Integer Classes = response.jsonPath().getInt("batchNoOfClasses"); 
        
    
 // Validate that values are either a String or null
    assertTrue(batchname == null || batchname instanceof String);
    assertTrue(BatchDescription == null || BatchDescription instanceof String);
    assertTrue(BatchStatus == null || BatchStatus instanceof String);
    assertTrue(ProgramName == null || ProgramName instanceof String);
    assertTrue(ProgramID == null || ProgramID instanceof Integer);
    assertTrue(BatchID != null || BatchID instanceof Integer);  
    assertTrue(Classes == null || BatchID instanceof Integer);    
    
}




//build request for Get batch by Batch Name request

public RequestSpecification buildGetByBatchName(String BatchNameTestcases) throws IOException
{	
	request = RestAssured.given()
		    .baseUri(baseURI) ;  		    
	createRequest = excelUtil.batchPojoUtil("Batch",BatchNameTestcases);	
	return request;
	
}

 // build request for Get batch by Batch ID  request

public RequestSpecification buildGetByBatchID(String ByBatchID) throws IOException
{
	request = RestAssured.given()
		    .baseUri(baseURI) ; 
	createRequest = excelUtil.batchPojoUtil("Batch",ByBatchID);	
	return request;
	
}

//build request for Get batch by Program Id request

public RequestSpecification buildGetByPGMId(String byPRGMIDtestcases) throws IOException
{	
	request = RestAssured.given()
		    .baseUri(baseURI) ;      
		    
	createRequest = excelUtil.batchPojoUtil("Batch",byPRGMIDtestcases);
	return request;
	
}

//build GetAll batches request

public RequestSpecification buildGetallBatchrequest(String GetTestCases) throws IOException
{		
	request = RestAssured.given()
		    .baseUri(baseURI) ;   
	createRequest = excelUtil.batchPojoUtil("Batch",GetTestCases);
	
	return request;
}


// Build Delete By Batch Id request

public RequestSpecification buildDelByBatchIdrequest(String DelByBatchIDtestCases) throws IOException {
	
	request = RestAssured.given()
		    .baseUri(baseURI) ;   
	createRequest = excelUtil.batchPojoUtil("Batch",DelByBatchIDtestCases);	
	return request;
	
}








//Sending request for Delete By Batch ID Request

public Response sendRequestDelByBatchID()
{
	logger.info("Delete By Batch ID  ----> ");
	String DelBatchEndpoint = (!createRequest.getEndpoint().equalsIgnoreCase("Valid")) ? createRequest.getEndpoint(): config.getString("DelByBatchId");
	String Authorization = (!createRequest.getAuthorizarion().equalsIgnoreCase("Valid")) ? null : getAdminToken();
	Integer BatchID=(createRequest.getBatchId()==null?batchIDArrList.get(0):createRequest.getBatchId());	
	
      logger.info("Batch Id for the Endpoint ---->"+ BatchID);
	if (null != Authorization) {
		request.header("Authorization", "Bearer " + Authorization);
	}
	response = request.when().delete(DelBatchEndpoint + BatchID);	
	return response;
}

//Delete By Batch ID Validation

public boolean deleteBybatchIDValidation(String ExpStatusCode)
{	
	String message = "";
	Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));
	logger.info("Response Code ------>" + response.getStatusCode());
	
	String responseBody = response.getBody().asString();	
	
	if(isValidJson(responseBody)) {
		JsonPath jsonPath = response.jsonPath();
		message = jsonPath.getString("message");
	} else message = responseBody;
		
	String SuccessMessgae="Batch with Id-"+ batchIDArrList.get(0)+" deleted Successfully!";
	String InvalidEndpointmesg="Invalid endpoint";
	String notfoundMesg="Batch id not found or not exists "+createRequest.getBatchId();		
	
	 boolean isValid = responseBody.contains(InvalidEndpointmesg) || 
             responseBody.contains(notfoundMesg);
	 
	 if(response.statusCode()==200) {
		 Assert.assertEquals(true,message.contains(SuccessMessgae) );	 
	 }else if(ExpStatusCode.equalsIgnoreCase("401")) {
		 Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));
	 }		 
	 else
		 Assert.assertTrue(isValid, "The response body is not valid: " + responseBody);
	 
	return true;
}



   //sending request for all the get methods(common method)

public Response batch_allGetRequest(String reqName) {	
	String Endpoint = (!createRequest.getEndpoint().equalsIgnoreCase("Valid")) ? createRequest.getEndpoint(): config.getString("createBatchEndpoint");
	String Authorization = (!createRequest.getAuthorizarion().equalsIgnoreCase("Valid")) ? null : getAdminToken();

	if (null != Authorization) {
		request.header("Authorization", "Bearer " + Authorization);
	}

	switch (reqName) {

	case "GetAllBatches":  logger.info("Get All Batches  Request-----> ");		
						String param = createRequest.getParam();
						if (null != param) {
						request.param("search", param);
									}
						response = request.when().get(Endpoint);
						break;
						
	case "BatchByBATCHID":	
		 			logger.info("Get Batch By Batch ID Request-----> ");
					String BatchIDEndpoint= (!createRequest.getEndpoint().equalsIgnoreCase("Valid")) ? createRequest.getEndpoint() :config.getString("GetByBatchID");
					Integer BatchID=(createRequest.getBatchId()==null?batchIDArrList.get(1):createRequest.getBatchId());
					logger.info("Batch Id for the Endpoint ---> " +BatchID);									
					response=request
							.when()                                      
							.get(BatchIDEndpoint + BatchID);	
					break;		
	
    case "BatchByBATCHNAME":  
    	   				logger.info("Get Batch By Batch Name Request-----> ");
    					String batchName=(createRequest.getCreatebatch().getBatchName()==null? batchNameArrList.get(0):createRequest.getCreatebatch().getBatchName());   					
    					String BatchNameEndpoint=(!createRequest.getEndpoint().equalsIgnoreCase("Valid"))? createRequest.getEndpoint() :config.getString("GetByBatchName");
    					logger.info("Batch Name for the Endpoint --> " +batchName);
    					response=request
    							.when()                                      
    							.get(BatchNameEndpoint + batchName);    					
    					break;
    					
    case "BatchByPGMID" :   
    	                logger.info("Get Batch By Program ID Request-----> ");
    					Integer PgmId=(createRequest.getCreatebatch().getProgramId()==null? programIDArrList.get(1):createRequest.getCreatebatch().getProgramId());    					
    					String PgmIdEndpoint=(!createRequest.getEndpoint().equalsIgnoreCase("Valid"))? createRequest.getEndpoint() :config.getString("GetbyProgramID");
    					logger.info("Program ID for the Endpoint --> " +PgmId);
    					response=request
    							.when()                                      
    							.get(PgmIdEndpoint + PgmId);    					
    							break;
	}
	
	return response;
}



}
