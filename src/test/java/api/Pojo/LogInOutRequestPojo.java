package api.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

//
//@Getter
//@Setter
public class LogInOutRequestPojo {

	@JsonIgnore
	private String baseUri;
	public String getBaseUri() {
		return baseUri;
	}
	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getExpectedCode() {
		return expectedCode;
	}
	public void setExpectedCode(String expectedCode) {
		this.expectedCode = expectedCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUserLoginEmailId() {
		return userLoginEmailId;
	}
	public void setUserLoginEmailId(String userLoginEmailId) {
		this.userLoginEmailId = userLoginEmailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonIgnore
	private String endPoint;
	@JsonIgnore
	private String testCaseId;
	@JsonIgnore
	private String expectedCode;
	@JsonIgnore
	private String action;

	private String userLoginEmailId;
	private String password;
}




