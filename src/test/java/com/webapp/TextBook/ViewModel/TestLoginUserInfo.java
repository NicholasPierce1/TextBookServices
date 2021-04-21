package com.webapp.TextBook.ViewModel;

import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.swing.text.html.Option;
import java.util.Optional;

/** Tets the logInUser viewmodel
 *
 */
public class TestLoginUserInfo {
    @Test
    public void testLoginViewModel() throws  Exception{
        JSONObject valid = new JSONObject();
        String user = "919123456";
        String pwd = "s123456";
        valid.put(LoginUserInfo.NOMINAL_USERNAME, user);
        valid.put(LoginUserInfo.NOMINAL_PASSWORD, pwd);
        Optional validResult = LoginUserInfo.createApiFromJson(valid, null);
        assert(validResult.isPresent());
        if(validResult.isPresent()){
            LoginUserInfo actual = (LoginUserInfo) validResult.get();
            LoginUserInfo expected = new LoginUserInfo(user, pwd);
            assert (actual.equals(expected));

        }
        //Testing for bad data
        JSONObject invalid = new JSONObject();
        invalid.put("Garbage", "Nonsense");
        Optional invalidResult = LoginUserInfo.createApiFromJson(invalid, null);
        assert (invalidResult.isEmpty());
    }
}
