package api.utility;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class CommonUtils  {
	public static String ProgramName;
	public static Integer ProgramId;
	public static Integer BatchID;
	public static String BatchName;	
	public static String adminToken;
	public static ResourceBundle config = ResourceBundle.getBundle("config");
		
	
	public static String baseURI = config.getString("baseUrl");	
	public static String userLoginEmailId = config.getString("userLoginEmailId");
	public static String password = config.getString("password");
	public static String loginEndPoint = config.getString("login");
	public static String dataFileUrl = config.getString("DataFile");
	
	
	public static Integer batchID1;
	public static String batchName1;	
	
	public static Integer batchID2;
	public static String batchName2;	
	

	public static ArrayList<Integer> batchIDArrList = new ArrayList<>();
	public static ArrayList<String> batchNameArrList = new ArrayList<>();
	
	public static Integer ClassId1;

	public static Integer ClassId2;

	public static ArrayList<Integer> ClassIDArrList = new ArrayList<>();

	
	public static Integer getClassId1() {
		return ClassId1;
	}

	public static void setClassId1(Integer classId1) {
		ClassId1 = classId1;
	}

	public static Integer getClassId2() {
		return ClassId2;
	}

	public static void setClassId2(Integer classId2) {
		ClassId2 = classId2;
	}
	
	public static String getAdminToken() {
		return adminToken;
	}

	public static void setAdminToken(String token) {
		adminToken = token;
		
	}
	
	public static Integer getBatchID1() {
		return batchID1;
	}

	public static void setBatchID1(Integer batchID1) {
		CommonUtils.batchID1 = batchID1;
	}

	public static String getBatchName1() {
		return batchName1;
	}

	public static void setBatchName1(String batchName1) {
		CommonUtils.batchName1 = batchName1;
	}

	public static Integer getBatchID2() {
		return batchID2;
	}

	public static void setBatchID2(Integer batchID2) {
		CommonUtils.batchID2 = batchID2;
	}

	public static String getBatchName2() {
		return batchName2;
	}

	public static void setBatchName2(String batchName2) {
		CommonUtils.batchName2 = batchName2;
	}
}
//public static String createClassEndPoint = config.getString("createClassEndpoint");
