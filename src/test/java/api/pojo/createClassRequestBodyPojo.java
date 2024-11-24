package api.pojo;

import java.time.LocalDateTime;
import java.util.List;



public class createClassRequestBodyPojo {
	private int batchId;
	private String classComments;
    private String classDate;
    private String classDescription;
    private int classNo;
    private String classNotes;
    private String classRecordingPath;
    private String classStaffId;
    private String classTopic;
    private List<String> classScheduledDates;

    public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getClassComments() {
		return classComments;
	}
	public void setClassComments(String classComments) {
		this.classComments = classComments;
	}
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public String getClassDescription() {
		return classDescription;
	}
	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}
	public int getClassNo() {
		return classNo;
	}
	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}
	public String getClassNotes() {
		return classNotes;
	}
	public void setClassNotes(String classNotes) {
		this.classNotes = classNotes;
	}
	public String getClassRecordingPath() {
		return classRecordingPath;
	}
	public void setClassRecordingPath(String classRecordingPath) {
		this.classRecordingPath = classRecordingPath;
	}
	public String getClassStaffId() {
		return classStaffId;
	}
	public void setClassStaffId(String classStaffId) {
		this.classStaffId = classStaffId;
	}
	public String getClassTopic() {
		return classTopic;
	}
	public void setClassTopic(String classTopic) {
		this.classTopic = classTopic;
	}
	public List<String> getClassScheduledDates() {
		return classScheduledDates;
	}
	public void setClassScheduledDates(List<String> classScheduledDates) {
		this.classScheduledDates = classScheduledDates;
	}


    
}