package api.Utility;

import java.util.ResourceBundle;

public class CommonUtils {
	
	public static String adminToken;
	public static ResourceBundle config = ResourceBundle.getBundle("config");
	
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
