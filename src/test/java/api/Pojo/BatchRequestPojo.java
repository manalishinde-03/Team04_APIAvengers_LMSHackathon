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
}
