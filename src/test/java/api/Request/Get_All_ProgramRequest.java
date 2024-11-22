package api.Request;

import api.Payload.UserLoginPayload;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_All_ProgramRequest extends CommonUtils {

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
    public Response sendRequest() {

        response = request.when().get(AllPrograms);

        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        System.out.println("Response :" +response.asPrettyString());
        System.out.println("StatusCode :" +statusCode);

        return response;
    }

    public Response sendinvalidRequest() {

        response = request.when().get(invalidprogramendpoint);

        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        System.out.println("Response :" +response.asPrettyString());
        System.out.println("Token :" +adminToken);

        return response;
    }
    public Response sendinvalidMethod() {

        response = request.when().post(AllPrograms);

        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();

        System.out.println("Response :" +response.asPrettyString());
        System.out.println("Token :" +adminToken);

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


