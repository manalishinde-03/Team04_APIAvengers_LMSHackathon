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
		public String getBatchDescription() {
			return batchDescription;
		}
		public void setBatchDescription(String batchDescription) {
			this.batchDescription = batchDescription;
		}
		public String getBatchName() {
			return batchName;
		}
		public void setBatchName(String batchName) {
			this.batchName = batchName;
		}
		public Integer getBatchNoOfClasses() {
			return batchNoOfClasses;
		}
		public void setBatchNoOfClasses(Integer batchNoOfClasses) {
			this.batchNoOfClasses = batchNoOfClasses;
		}
		public String getBatchStatus() {
			return batchStatus;
		}
		public void setBatchStatus(String batchStatus) {
			this.batchStatus = batchStatus;
		}
		public Integer getProgramId() {
			return programId;
		}
		public void setProgramId(Integer programId) {
			this.programId = programId;
		}
		public String getProgramName() {
			return programName;
		}
		public void setProgramName(String programName) {
			this.programName = programName;
		}
		private String batchStatus;
		private Integer programId;
		private String programName;
	
}
