package api.Pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequestPojo {
	
	private BatchRequestBodyPojo createbatch;
	private String Endpoint;
	private String Authorizarion;
	private Integer StatusCode;
	
	@Override
	public String toString() {
		return "BatchRequestPojo [createbatch=" + createbatch + ", Endpoint=" + Endpoint + ", Authorizarion="
				+ Authorizarion + ", StatusCode=" + StatusCode + "]";
	}
}
