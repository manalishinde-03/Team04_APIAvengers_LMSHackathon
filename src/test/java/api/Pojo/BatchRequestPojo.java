package api.Pojo;

import org.apache.poi.ss.usermodel.Cell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequestPojo {
	
	private BatchRequestBodyPojo createbatch;
	
	private String Endpoint;
	private String Authorizarion;
	private Integer StatusCode;
	private String param;
	private Integer batchId;
	
	@Override
	public String toString() {
		return "BatchRequestPojo [createbatch=" + createbatch + ", Endpoint=" + Endpoint + ", Authorizarion="
				+ Authorizarion + ", StatusCode=" + StatusCode + "]";
	}
}
