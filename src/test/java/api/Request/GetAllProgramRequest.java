package api.Request;

import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllProgramRequest extends CommonUtils {

    public static String adminToken;
    public Response response;
    private RequestSpecification request;
    int statusCode;
    String statusLine;
    LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
    public RequestSpecification buildRequest() {
        RestAssured.baseURI = baseURI;

        request = RestAssured.given()
                .baseUri(baseURI)
                .header("Content-Type", "application/json")
                .auth().oauth2(getAdminToken());

        System.out.println("Request :" +request.log().all());
        return request;

    }
    public Response sendRequest(String method, String endPoint) {

        if ("GET".equalsIgnoreCase(method)) {
            response = request.when().get(endPoint); //AllPrograms);
        }
        else if ("POST".equalsIgnoreCase(method)) {
            response = request.when().post(endPoint); //AllPrograms);
        }

        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        System.out.println("Response :" +response.asPrettyString());
        System.out.println("StatusCode :" +statusCode);

        return response;
    }

    public RequestSpecification buildNoAuthRequest() {
        RestAssured.baseURI = baseURI;

        request = RestAssured.given()
                .baseUri(baseURI)
                .header("Content-Type", "application/json")
                .auth().none();

        System.out.println("Request :" +request.log().all());
        return request;

    }
}


