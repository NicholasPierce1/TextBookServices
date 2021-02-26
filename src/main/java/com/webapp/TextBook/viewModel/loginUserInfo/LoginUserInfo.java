package com.webapp.TextBook.viewModel.loginUserInfo;

import com.webapp.TextBook.validation.loginUserInfoValidation.LogInUserInfoValidationInterface;

@LogInUserInfoValidationInterface
public class LoginUserInfo {
    public static String Nominal_Username, Nominal_Password, username,password;
    public LoginUserInfo(){

    }
    public  LoginUserInfo(String Nominal_Username, String Nominal_Password, String username, String password){
        this.Nominal_Username = Nominal_Username;
        this.Nominal_Password = Nominal_Password;
        this.username = username;
        this.password = password;



    }

    public static String getNominal_Username() {
        return Nominal_Username;
    }

    public static void setNominal_Username(String nominal_Username) {
        Nominal_Username = nominal_Username;
    }

    public static String getNominal_Password() {
        return Nominal_Password;
    }

    public static void setNominal_Password(String nominal_Password) {
        Nominal_Password = nominal_Password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginUserInfo.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LoginUserInfo.password = password;
    }

}
