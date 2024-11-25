package api.Payload;

import static io.restassured.RestAssured.given;

import api.Pojo.CreateProgramRequestPojo;
import api.Utility.CommonUtils;

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
