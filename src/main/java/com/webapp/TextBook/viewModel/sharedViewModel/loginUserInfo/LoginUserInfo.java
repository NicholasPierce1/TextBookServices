package com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo;

import com.sun.istack.Nullable;
import com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation.LogInUserInfoValidationInterface;
import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import com.webapp.TextBook.viewModel.shared.ApiViewModelCreation;
import com.webapp.TextBook.viewModel.shared.FormViewModel;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@LogInUserInfoValidationInterface
public class LoginUserInfo extends ApiViewModelCreation implements FormViewModel{

        // enumerates static members
        public final static String NOMINAL_USERNAME = "_username";
        public final static String NOMINAL_PASSWORD = "_password";


        private String _username;
        private String _password;

    /**
     * <h1>valueStateSetter</h1>
     * <h3>type: Consumer( type params: Pair (type params: LoginUserInfo & JSONObject)</h3>
     *
     * <p>
     *     Description: From a LoginUserInfo object & its corresponding JSONObject (should be a
     *     LoginUserInfo reflection/composition in JSON form), attempts to set and update state within
     *     LoginUserInfo from the JSON form.
     * </p>
     *
     * throws: RuntimeException -- if the JSON object does not retain the necessary keys to holistically
     * update a LoginUserInfo
     */
    private static Consumer<Pair<LoginUserInfo, JSONObject>> valueStateSetter = (studentInfoPair -> {

        // parses data from json and set into login user info

        // holds local copy of json object & login user info
        final LoginUserInfo LOGIN_USER_INFO = studentInfoPair.getValue0();
        final JSONObject DATA = studentInfoPair.getValue1();

        // integrity checks that key values required by LoginUserInfo are present
        // throws exception if not
        if(!DATA.has(LoginUserInfo.NOMINAL_PASSWORD) || !DATA.has(LoginUserInfo.NOMINAL_USERNAME))
            throw new RuntimeException("LoginUserInfo's ValueStateSetter: nominal key values required for" +
                    "LoginUserInfo updating are not present. Please refer to data contract and data definition in controller" +
                    " for clarification of data state expectations.");

        // sets parse state (try - catch BUT will never occur -- runtime check above handles)
        try {
            LOGIN_USER_INFO.set_password(DATA.getString(LoginUserInfo.NOMINAL_PASSWORD));
            LOGIN_USER_INFO.set_username(DATA.getString(LoginUserInfo.NOMINAL_USERNAME));
        }
        catch(JSONException ex){
            // will never occur
            System.out.println("Internal Error (LoginUserInfo -- LoginUserInfo's ValueStateSetter):\n" +
                    "an error occurred that should be caught, thrown and handled internally. Please check stack trace " +
                    "for more info." + ex.getLocalizedMessage());
        }

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



    /**
     * <p>
     *     From the JSONObject (should be JSON composition/reflection of LoginUserInfo's state)
     *     and an optional Supplier, render and sets a LoginUserInfo object to the reflective/corresponding
     *     state within the JSONObject.
     * </p>
     * @param jsonObject JSONObject which is the composition of the state that defines a LoginUserInfo
     * @param loginUserInfoSupplier Supplier(type param: LoginUserInfo) optional function to invoke to supplement
     *                              a LoginUserInfo to use in creation and state setting.
     * @return Optional(type param: LoginUserInfo) holding the ApiViewModel object. Will be empty if
     * a) JSONObject does not hold the necessary state for a full state setting
     * b) Supplier(type param: LoginUserInfo) throws an internal error
     */
    public static @NotNull Optional<LoginUserInfo> createApiFromJson(
            @NotNull JSONObject jsonObject,
            @Nullable Supplier<LoginUserInfo> loginUserInfoSupplier){

        // creates a local supplier based on the predicate if the given supplier is null or not
        // use the blank constructor as a default
        final Supplier<LoginUserInfo> localLoginUserInfoSupplier = loginUserInfoSupplier == null ?
                LoginUserInfo::new :
                loginUserInfoSupplier;

        // invokes parent's static helper to set the state for a LoginUserInfo
        return ApiViewModelCreation.createApiViewModelFromJson(
                jsonObject,
                localLoginUserInfoSupplier,
                LoginUserInfo.valueStateSetter
        );

    }


    /**
     * <p>
     *     From the JSONObject (should be JSON composition/reflection of LoginUserInfo's state)
     *     and an optional Supplier, render and sets a LoginUserInfo object to the reflective/corresponding
     *     state within the JSONObject.
     * </p>
     *
     * @param jsonObject JSONObject which is the composition of the state that defines a LoginUserInfo
     *
     * @return Optional(type param : LoginUserInfo) holding the ApiViewModel object. Will be empty if
     * a) JSONObject does not hold the necessary state for a full state setting
     */
    public static @NotNull Optional<LoginUserInfo> createApiFromJson(
            @NotNull JSONObject jsonObject) {

        // invokes overloaded function for state setting
        return createApiFromJson(jsonObject, null);

    }


    /**
     * .equals method to compare fields for testing
     */
    @Override
    public boolean equals(Object loginObj){
        LoginUserInfo expected = (LoginUserInfo) loginObj;
        //compare the actual and expected values
        return (
                this._password.equals(expected.get_password())
                && this._username.equals(expected.get_username())
        );

    }

    /**
     * <p>
     *     From a form data capture Map, create a LoginUserInfo
     * </p>
     * @param formData: form data capture map.
     *                Must hold key values that match the nominal key values (field names)
     * @return a LoginUserInfo representing the corresponding state embedded within the form data capture
     * @throws Exception if formData retains a key that is not a field name or formData is incomplete
     * (field values remain unset).
     */
    public static @NotNull LoginUserInfo createWebFromForm(
            @NotNull final MultiValueMap<String, Object> formData)throws Exception{

        // creates initial login user info
        final LoginUserInfo loginUserInfo = new LoginUserInfo();

        // iterates over all form value pairs and set them according to their key value
        for(String key : formData.keySet())
            switch(key){
                case LoginUserInfo.NOMINAL_USERNAME:
                    loginUserInfo.set_username((String)formData.get(key).get(0));
                    break;
                case LoginUserInfo.NOMINAL_PASSWORD:
                    loginUserInfo.set_password((String)formData.get(key).get(0));
                    break;
                default:
                    throw new Exception("Key value: {" + key + "} does not match any of the field's names." +
                            "Please revise.");
            }

        if(loginUserInfo.get_username() == null || loginUserInfo.get_password() == null)
            throw new Exception("Form data capture is incomplete. Please make sure the form data retains" +
                    "keys for all fields that comprise a LoginUserInfo.");

        return loginUserInfo;

    }

    @Override
    public @NotNull JSONObject createJsonObjectForm() throws JSONException{

        // create a new json object, and use the nominal keys to place the instance/field in
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put(LoginUserInfo.NOMINAL_USERNAME, this.get_username());
        jsonObject.put(LoginUserInfo.NOMINAL_PASSWORD, this.get_password());

        return jsonObject;

    }
}
