package com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo;

import com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation.LogInUserInfoValidationInterface;
import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import com.webapp.TextBook.viewModel.shared.ApiViewModelCreation;
import com.webapp.TextBook.viewModel.shared.FormViewModel;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@LogInUserInfoValidationInterface
public class LoginUserInfo extends ApiViewModelCreation implements FormViewModel{

        // enumerates static members
        public final static String NOMINAL_USERNAME = "username";
        public final static String NOMINAL_PASSWORD = "password";


        private String _username;
        private String _password;

    // todo: doc here
    private static Consumer<Pair<StudentInfo, JSONObject>> valueStateSetter = (studentInfoPair -> {
        // todo: implement (parse data from json and set into student info)

    });

    public LoginUserInfo(){

    }

    public  LoginUserInfo(String username, String password){
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

    /*
    @Override
    public @NotNull Optional<LoginUserInfo> createApiViewModelFromJson(@NotNull JSONObject jsonObject, Supplier<LoginUserInfo> initialInstantiation) {
        // instantiates empty user for partial construction
        final LoginUserInfo user = new LoginUserInfo();

        // try-catch for JSON key errors in parsing partial user definition
        try{

            user.set_password(jsonObject.getString(LoginUserInfo.NOMINAL_PASSWORD));

            user.set_username(jsonObject.getString(LoginUserInfo.NOMINAL_USERNAME));

            return Optional.of(user);

        }
        catch(JSONException jsonException){
            // logs (prints now) exception
            System.out.println(jsonException.getMessage());

            return Optional.empty();
        }
    }
     */

    // todo: doc and comment set here
    public static @NotNull Optional<StudentInfo> createApiFromJson(
            @NotNull JSONObject jsonObject,
            @NotNull Supplier<StudentInfo> studentInfoSupplier){
        try{
            return ApiViewModelCreation.createApiViewModelFromJson(
                    jsonObject,
                    studentInfoSupplier,
                    LoginUserInfo.valueStateSetter
            );
        }
        catch(RuntimeException ex){
            // todo: log w/ internal error
            return Optional.empty();
        }
    }

}
