package api.Utility;

import java.util.ResourceBundle;

public class CommonUtils {
	
	public static String adminToken;
	private static int programID;
	public static String programName;
	public static String programStatus;
	public static ResourceBundle config = ResourceBundle.getBundle("config");
	
	public static String baseURI = config.getString("baseUrl");

	
	public static String userLoginEmailId = config.getString("userLoginEmailId");
	public static String password = config.getString("password");
	public static String loginEndPoint = config.getString("login");
	public static String createProgramEndPoint = config.getString("program");
	
	
	
	
	public static int getProgramID() {
		System.out.println(">>>>>> in Get Program ID");
		return programID;
	}

	public static void setProgramID(int programID) {
		System.out.println(">>>>>> in Set Program ID");
		CommonUtils.programID = programID;
	}
	

	public static String getProgramName() {
		return programName;
	}

	public static void setProgramName(String programName) {
		CommonUtils.programName = programName;
	}

	public static String getProgramStatus() {
		return programStatus;
	}

	public static void setProgramStatus(String programStatus) {
		CommonUtils.programStatus = programStatus;
	}

	public static String getAdminToken() {
		return adminToken;
	}

	public static void setAdminToken(String token) {
		adminToken = token;
	}

}
