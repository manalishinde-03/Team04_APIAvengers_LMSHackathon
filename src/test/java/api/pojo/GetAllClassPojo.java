package api.pojo;

public class GetAllClassPojo {

	private String Authorization;
	private Integer StatusCode;
	private String Method;
	private String Endpoint;


	public String getEndpoint() {
		return Endpoint;
	}
	public void setEndpoint(String endpoint) {
		Endpoint = endpoint;
	}
	public String getAuthorization() {
		return Authorization;
	}
	public void setAuthorization(String authorization) {
		Authorization = authorization;
	}
	public Integer getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(Integer statusCode) {
		StatusCode = statusCode;
	}
	public String getMethod() {
		return Method;
	}
	public void setMethod(String method) {
		Method = method;
	}
	

}
