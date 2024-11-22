package api.Payload;

import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;

public class UserLoginPayload extends CommonUtils {


    public static LoginRequestPojo adminLogin() {

        LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
        loginRequestPojo.setUserLoginEmailId(userLoginEmailId);
        loginRequestPojo.setPassword(password);
        return loginRequestPojo;
    }

    public static LoginRequestPojo invalidLogin() {

        LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
        loginRequestPojo.setUserLoginEmailId(userLoginEmailId);
        loginRequestPojo.setPassword(invalidpassword);
        return loginRequestPojo;
    }
}
