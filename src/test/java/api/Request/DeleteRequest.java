package api.Request;

import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class DeleteRequest {

	private RequestSpecification request;
	public Response response;

	public static String token() {

		RestAssured.baseURI = CommonUtils.baseURI;
		String requestBody = "{\n" + "\"userLoginEmailId\": \"sdet@gmail.com\",\n"
				+ "\"password\":\"LmsHackathonApi@2024\"}\n" + "";

		Response res = RestAssured.given().baseUri(CommonUtils.baseURI).header("Content-Type", "application/json")
				.body(requestBody).when().post("/login").then().assertThat().extract().response();

		System.out.println(res.asString());
		JsonPath js = res.jsonPath();
		String authtoken = js.getString("token");
		System.out.println(authtoken);

		return authtoken;

	}

	public static Response buildRequest(String endpoint, String methodType, String tokenType) {
		RestAssured.baseURI = CommonUtils.baseURI;

		RequestSpecification request = RestAssured.given().baseUri(CommonUtils.baseURI)
				.header("Content-Type", "application/json").log().all(); // Logs request details

		// Modify the request based on token type
		switch (tokenType.toLowerCase()) {
		case "bearer":
			request.header("Authorization", "Bearer " + token()); // Add Bearer token
			break;

		case "noauth":
			// Do nothing; no Authorization header for noauth
			break;

		default:
			throw new IllegalArgumentException("Invalid token type: " + tokenType);
		}

		switch (methodType.toUpperCase()) {
		case "GET":
			return request.when().get(endpoint).then().extract().response();
		case "POST":
			return request.when().post(endpoint).then().extract().response();
		case "PUT":
			return request.when().put(endpoint).then().extract().response();
		case "DELETE":
			return request.when().delete(endpoint).then().extract().response();
		default:
			throw new IllegalArgumentException("Invalid HTTP method type: " + methodType);
		}

	}
}

