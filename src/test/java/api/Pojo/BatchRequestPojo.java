package api.Pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequestPojo {
	
	private BatchRequestBodyPojo createbatch;
	private String Endpoint;
	private String Authorizarion;
	
	private String param;
	private Integer batchId;
	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public BatchRequestBodyPojo getCreatebatch() {
		return createbatch;
	}

	public void setCreatebatch(BatchRequestBodyPojo createbatch) {
		this.createbatch = createbatch;
	}

	public String getEndpoint() {
		return Endpoint;
	}

	public void setEndpoint(String endpoint) {
		Endpoint = endpoint;
	}

	public String getAuthorizarion() {
		return Authorizarion;
	}

	public void setAuthorizarion(String authorizarion) {
		Authorizarion = authorizarion;
	}

	public Integer getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(Integer statusCode) {
		StatusCode = statusCode;
	}

	private Integer StatusCode;
	
	@Override
	public String toString() {
		return "BatchRequestPojo [createbatch=" + createbatch + ", Endpoint=" + Endpoint + ", Authorizarion="
				+ Authorizarion + ", StatusCode=" + StatusCode + "]";
	}
}
