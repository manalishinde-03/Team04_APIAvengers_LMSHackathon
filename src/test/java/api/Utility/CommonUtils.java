package api.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import api.Pojo.BatchRequestPojo;
import api.Pojo.BatchRequestBodyPojo;
import lombok.Getter;
import lombok.Setter;

public class CommonUtils {	
	
	public static Integer batchID1;
	public static String batchName1;	
	
	public static Integer batchID2;
	public static String batchName2;	
	
	public static String programName1;
	public static Integer programID1;
	
	public static String programName2;
	public static Integer programID2;
	
	public static String programName;
	public static Integer programId;
	
	public static ArrayList<Integer> programIDArrList = new ArrayList<>();
	public static ArrayList<String> programNameArrList = new ArrayList<>();

	public static ArrayList<Integer> batchIDArrList = new ArrayList<>();
	public static ArrayList<String> batchNameArrList = new ArrayList<>();
	
	
    public static String getProgramName() {
		return programName;
	}

	public static void setProgramName(String programName) {
		CommonUtils.programName = programName;
	}

	public static Integer getProgramId() {
		return programId;
	}

	public static void setProgramId(Integer programId) {
		CommonUtils.programId = programId;
	}


	public static String adminToken;
	
	private static Logger logger;
	
    public static ResourceBundle config = ResourceBundle.getBundle("config");	
	
	public static String baseURI = config.getString("baseUrl");

	
	public static String userLoginEmailId = config.getString("userLoginEmailId");
	public static String password = config.getString("password");
	public static String loginEndPoint = config.getString("login");
	
	
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
	
}
