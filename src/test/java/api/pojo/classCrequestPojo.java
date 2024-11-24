package api.pojo;

public class classCrequestPojo {

	private createClassRequestBodyPojo createClass;
	private String Endpoint;
	private String Authorization;
	private Integer StatusCode;

	public createClassRequestBodyPojo getCreateClass() {
		return createClass;
	}

	public void setCreateClass(createClassRequestBodyPojo createClass) {
		this.createClass = createClass;
	}

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

//	@Override
//	public String toString() {
//		return "classCrequestPojo [createclass=" + createclass + ", Endpoint=" + Endpoint + ", Authorization="
//				+ Authorization + ", StatusCode=" + StatusCode + "]";
//	}
}
