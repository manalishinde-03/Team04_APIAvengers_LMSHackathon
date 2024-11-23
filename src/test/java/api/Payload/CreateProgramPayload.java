package api.Payload;

import static io.restassured.RestAssured.given;

import java.util.Map;

import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.http.ContentType;

public class CreateProgramPayload extends CommonUtils {
	
	static CreateProgramRequestPojo createProgramRequestPojo = new CreateProgramRequestPojo();
	
	public static int programID;
	public static String programName;
	public static String programStatus;
	public static String programDescription;
	
	public static CreateProgramRequestPojo createProgram(){
		
		
		createProgramRequestPojo.setProgramDescription("DataScience");
		createProgramRequestPojo.setProgramName("DataA");
		createProgramRequestPojo.setProgramStatus("Active");
		
		return createProgramRequestPojo;
	}
	
public static CreateProgramRequestPojo createProgramWithMandatoryDetails(){
		
		
		createProgramRequestPojo.setProgramName("TeachingZ");
		createProgramRequestPojo.setProgramStatus("Active");
		
		return createProgramRequestPojo;
	}

public static CreateProgramRequestPojo createProgramExcel(){
	
	
	createProgramRequestPojo.setProgramDescription("DataScience");
	createProgramRequestPojo.setProgramName("DataA");
	createProgramRequestPojo.setProgramStatus("Active");
	
	return createProgramRequestPojo;
}



public static CreateProgramRequestPojo createProgramWithExcel(){
	
	
	createProgramRequestPojo.setProgramDescription(programDescription);
	createProgramRequestPojo.setProgramName(programName);
	createProgramRequestPojo.setProgramStatus(programStatus);
	
	return createProgramRequestPojo;
}

}
