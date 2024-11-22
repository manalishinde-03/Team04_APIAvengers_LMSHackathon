package api.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.Pojo.BatchRequestPojo;
import api.Pojo.BatchRequestBodyPojo;
import lombok.Getter;
import lombok.Setter;

public class CommonUtils {	
	
	
	public static String ProgramName;
	public static Integer ProgramId;
	public static Integer batchID;
	public static String batchName;	
	
	public static String adminToken;
	
	public static ResourceBundle config = ResourceBundle.getBundle("config");
	
	public static String getProgramName() {
		return ProgramName;
	}

	public static void setProgramName(String programName) {
		ProgramName = programName;
	}

	public static Integer getProgramId() {
		return ProgramId;
	}

	public static void setProgramId(Integer programId) {
		ProgramId = programId;
	}

	public static String baseURI = config.getString("baseUrl");

	
	public static String userLoginEmailId = config.getString("userLoginEmailId");
	public static String password = config.getString("password");
	public static String loginEndPoint = config.getString("login");
	
	
	
	
	public static String getAdminToken() {
		return adminToken;
	}

	public static void setAdminToken(String token) {
		adminToken = token;
	}

	
	
	
	
	
	
}
