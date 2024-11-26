package api.Pojo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class BatchRequestBodyPojo {
		
		private String batchDescription;		
		private String batchName;
		private Integer batchNoOfClasses;
		private String batchStatus;
		private Integer programId;
		private String programName;
	
}
