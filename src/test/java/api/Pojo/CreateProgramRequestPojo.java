package api.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateProgramRequestPojo {

	private String programDescription;
	private String programName;
	private String programStatus;

	@JsonIgnore
	private String endPoint;

	@JsonIgnore
	private String testCaseId;
	@JsonIgnore
	private String action;
	@JsonIgnore
	private String method;
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

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
