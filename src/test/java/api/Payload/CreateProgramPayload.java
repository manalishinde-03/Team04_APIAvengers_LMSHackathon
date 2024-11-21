package api.Payload;

import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;

public class CreateProgramPayload extends CommonUtils {
	
	static CreateProgramRequestPojo createProgramRequestPojo = new CreateProgramRequestPojo();
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

}
