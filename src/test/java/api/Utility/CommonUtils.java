package api.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class CommonUtils {	
	
	
	public static ArrayList<Integer> programIDArrList = new ArrayList<>();
	public static ArrayList<String> programNameArrList = new ArrayList<>();

	public static ArrayList<Integer> batchIDArrList = new ArrayList<>();
	public static ArrayList<String> batchNameArrList = new ArrayList<>();
	
	
	public static String adminToken;
	
	private static Logger logger;
	
    public static ResourceBundle config = ResourceBundle.getBundle("config");	
	
	public static String baseURI = config.getString("baseUrl");

	
	public static String userLoginEmailId = config.getString("userLoginEmailId");
	public static String password = config.getString("password");
	public static String loginEndPoint = config.getString("login");
	
	
	public static String createProgramEndPoint = config.getString("program");

	public static String excelFilePath = config.getString("Excel_Path");

	public static void setProgramID(int programId) {

		programIDArrList.add(programId);
		System.out.println("In Set programID >>>" + programIDArrList);
	}

	public static ArrayList<Integer> getProgramID() {

		System.out.println("In Get >>>" + programIDArrList);
		return programIDArrList;
	}

	public static void setProgramName(String programName) {

		programNameArrList.add(programName);
		System.out.println("In Set programName >>>" + programNameArrList);
	}

	public static ArrayList<String> getProgramName() {

		System.out.println("In Get >>>" + programNameArrList);
		return programNameArrList;
	}

	public static String getAdminToken() {
		return adminToken;
	}

	public static void setAdminToken(String token) {
		adminToken = token;
	}

	public static Logger getLogger() {
		logger = LogManager.getLogger();
		return logger;
	}
	
	public static boolean isValidJson(String jsonString) {
        try {
        	if(null != jsonString && !jsonString.equalsIgnoreCase("") && jsonString.length()>0) {
        		JsonParser parser = new JsonParser();
            	parser.parse(jsonString);
            	return true;
            } else return false;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
	

	public static Map<String, String> getDefaultHeaders() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		 headers.put("Authorization", "Bearer " + getAdminToken());

		return headers;
	}

	public static Map<String, String> getHeadersWithoutAuth() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return headers;
	}

	
	public static Integer batchID1;
	public static String batchName1;	
	
	public static Integer batchID2;
	public static String batchName2;	
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

