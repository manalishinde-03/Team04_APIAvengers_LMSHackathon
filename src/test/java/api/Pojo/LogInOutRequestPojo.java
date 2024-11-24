package api.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInOutRequestPojo {

	@JsonIgnore
	private String baseUri;
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




