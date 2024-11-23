package api.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateProgramRequestPojo {

	private String programDescription;
	private String programName;
	private String programStatus;
	
	@JsonIgnore
	private String endpoint;

	@JsonIgnore
	private String testCaseId;
	
	@JsonIgnore
	private String expectedStatusCode;

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}


	public String getExpectedStatusCode() {
		return expectedStatusCode;
	}

	public void setExpectedStatusCode(String expectedStatusCode) {
		this.expectedStatusCode = expectedStatusCode;
	}

	public String getProgramDescription() {
		return programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getEndpoint() {
        return endpoint; // Getter for Endpoint
    }
	
	public void setEndpoint(String endpoint) {
		 this.endpoint = endpoint;
		
	}

}
