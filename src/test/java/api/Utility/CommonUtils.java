package api.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import lombok.Getter;
import lombok.Setter;

public class CommonUtils {

	public static String adminToken;
	private static int programID1;
	public static String programName1;

	private static int programID2;
	public static String programName2;
	public static String programStatus;

	@Getter
	@Setter
	public static Integer batchID;
	public static String batchName;

	public static ResourceBundle config = ResourceBundle.getBundle("config");

	public static String baseURI = config.getString("baseUrl");

	public static ArrayList<Integer> programIDArrList = new ArrayList<>();
	public static ArrayList<String> programNameArrList = new ArrayList<>();

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

}
