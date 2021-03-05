package com.webapp.TextBook.viewModel.formViewModel.loginUserInfo;

import com.webapp.TextBook.validation.FormVallidation.loginUserInfoValidation.LogInUserInfoValidationInterface;
import com.webapp.TextBook.viewModel.shared.FormViewModel;

@LogInUserInfoValidationInterface
public class LoginUserInfo implements FormViewModel {

        // enumerates static members
        public final static String NOMINAL_USERNAME = "username";
        public final static String NONMINAL_PASSWORD = "password";


        private String _username;
        private String _password;

    public LoginUserInfo(){

    }
    public  LoginUserInfo(String Nominal_Username, String Nominal_Password, String username, String password){
        this._username = username;
        this._password = password;

    }

    public String get_password() {
        return _password;
    }

    public String get_username() {
        return _username;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
}
