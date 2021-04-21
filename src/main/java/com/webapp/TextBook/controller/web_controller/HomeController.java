package com.webapp.TextBook.controller.web_controller;


import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import org.javatuples.KeyValue;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.*;


import javax.validation.Valid;

import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;

@Controller
@RequestMapping(path = "/home/")
public class HomeController {

    // todo: remove after testing
    @Autowired
    private ApiValidationHandler _validator;

    @RequestMapping(value="/testLoginHere/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String testFormPost(
            @RequestBody(required = false) MultiValueMap<String, Object> dataReceived,
            ModelMap modelMap
            ){

        System.out.println(dataReceived == null);

        if(dataReceived != null) {
            for (String keyValue : dataReceived.keySet()) {
                System.out.println(keyValue + " : " + dataReceived.get(keyValue).get(0));
                System.out.println(dataReceived.get(keyValue).get(0) instanceof String);
            }
            try {
                final LoginUserInfo loginUserInfo = LoginUserInfo.createWebFromForm(dataReceived);
                System.out.println(loginUserInfo.get_password() + " : " + loginUserInfo.get_username());

                final JSONObject jsonObject = new JSONObject();

                System.out.println("validation working: " + ValidationBindingHelper.handleApiValidationBindingForJsonOutput(
                        this._validator.getApiBindingError(loginUserInfo),
                        jsonObject
                ));
                System.out.println(jsonObject);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        modelMap.addAttribute("data", new JSONObject());


        return "login";
    }

    @RequestMapping(value = "/testLogin/{toReturn}", method = RequestMethod.GET)
    public String index(
            @PathVariable(required = false, name = "toReturn") Optional<String> toReturn,
            ModelMap modelMap){

        modelMap.addAttribute("test", "value test here");

        Integer toReturnNum = toReturn.isEmpty() ? null : Integer.parseInt(toReturn.get());

        final JSONObject data = new JSONObject();

        try {
            data.put("GeneralErrors", null);
            data.put("Errors", null);


            if(toReturnNum != null){

                // enumerates local final strings as the keys to error bindings in json
                final String errorBindingNameKey = "fieldName";
                final String errorBindingMessageKey = "message";
                final String errorBindingFaultyDateKey = "faultyData";

                // denotes general errors default value
                final String generalErrorsValue = "whoops!!";

                // enumerates local final error bindings representing errors within login attempts

                // username: (supports refreshing)
                final JSONObject usernameErrorBinding = new JSONObject();
                usernameErrorBinding.put(errorBindingNameKey, "_username");
                usernameErrorBinding.put(errorBindingMessageKey, "username oops");
                usernameErrorBinding.put(errorBindingFaultyDateKey, "faulty data here");

                // password: (no refreshing)
                final JSONObject passwordErrorBinding = new JSONObject();
                passwordErrorBinding.put(errorBindingNameKey, "_password");
                passwordErrorBinding.put(errorBindingMessageKey, "password oops");
                passwordErrorBinding.put(errorBindingFaultyDateKey, null);

                // creates local final json array to hold error bindings
                final JSONArray errorBindingArray = new JSONArray();


                // 1: errors set (1 field)
                // 2: errors set (2 fields)
                // 3: general errors
                switch(toReturnNum){
                    case 1:
                        errorBindingArray.put(usernameErrorBinding);
                        data.put("Errors", errorBindingArray.toString());
                        break;
                    case 2:
                        errorBindingArray.put(usernameErrorBinding);
                        errorBindingArray.put(passwordErrorBinding);
                        data.put("Errors", errorBindingArray.toString());
                        break;
                    case 3:
                        System.out.print("3 called");
                        data.put("GeneralError", generalErrorsValue);
                        break;
                    case 4:
                        break;

                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }

        modelMap.addAttribute("data", data);

        System.out.println("called");
        return "login";
    }


    // fetching shared adapter for encapsulated, database interaction
    @Autowired Adapter adapter;

    //login page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap modelMap){

        final JSONObject data = new JSONObject();
        System.out.println("login get called");
        modelMap.addAttribute("data", data);

        return "login";

    }

    //login confirmation
    @RequestMapping(value = "/", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String loginConfirmation (
            @RequestBody @Valid @ModelAttribute("LoginUserInfo") LoginUserInfo person,
            BindingResult result,
            ModelMap map) throws JSONException {
        JSONObject data = new JSONObject();
        // handling all exceptions with try catch block
        try {
            System.out.println("login confirmed called");

            // format validation/dealing with binding errors
            Pair<Boolean,JSONObject> validationResult = ValidationBindingHelper.validationBindingHandler(result,map);
            if(!validationResult.getValue0()){
                validationResult.getValue1().put("LoginUserInfo", null);
                System.out.println("returning login view");
                return "login";
            }

            System.out.println("validation good");
            if(true)
                return "redirect:/home/testLogin/4";

            // checking user data validity with database
            Pair<Optional<User>, StatusCode> user = adapter.userLogin(person.get_username(), person.get_password());

            //interpreting data
            if (user.getValue1() == StatusCode.OK) {
                // User login was valid and will be further processed
                data.put("LoginUserInfo", person);
                data.put("StatusMessage", user.getValue1().getContentMessage());
                data.put("GeneralError",null);
                data.put("Errors", null);

                map.addAttribute("data", data);

                //setting user session data
                SharedSessionData.setSessionValueWithKey(SharedSessionData.USER_KEY, user.getValue0().orElseThrow());

                //checking user Role
                if (user.getValue0().get().userRole == UserRole.Supervisor) {
                    // User Role is Supervisor and will continue as such
                    return "SupervisorDropDownMenu";
                } else {
                    // User Role is Student Employee and will continue as such
                    return "StudentDropDownMenu";
                }
            } else {
                // User login was invalid for reason stated in status message
                // return to login
                data.put("LoginUserInfo", null);
                data.put("StatusMessage", user.getValue1().getContentMessage());
                data.put("GeneralError",null);
                data.put("Errors", null);

                map.addAttribute("data", data);

                return "login";
            }
        }
        catch (Exception e){
            //Catching Exceptions and printing StackTrace of the error
            //Loading in custom status message and returning to login page
            System.out.println(e.getMessage());
            data.put("LoginUserInfo", null);
            data.put("StatusMessage", "Internal error has occurred. " +
                    "If this continues please contact your IT support.");
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("data", data);

            return "login";
        }
    }


    @RequestMapping(value = "/testCheckInOut", method = RequestMethod.GET)
    public String testCheckInOut(ModelMap modelMap){

        // known: if this page loads then no errors uncovered
        // render login user info and stashed inside json object

        // enumerates model map keys & login user info keys
        final String modelMapDataKey = "data";
        final JSONObject modelMapDataValue = new JSONObject();
        final String loginUserInfoJsonKey = "LoginUserInfo";
        final String loginUserInfoUsernameKey = "_username";
        final String loginUserInfoPasswordKey = "_password";

        // creates login user info json object equivalent
        final JSONObject loginUserInfoJson = new JSONObject();

        try{

            // sets login user info state
            loginUserInfoJson.put(loginUserInfoUsernameKey, "username");
            loginUserInfoJson.put(loginUserInfoPasswordKey, "password");

            // appends login user info json to model map json
            modelMapDataValue.put(loginUserInfoJsonKey,loginUserInfoJson);

            // appends model map json into model map
            modelMap.put(modelMapDataKey, modelMapDataValue);

        }
        catch(JSONException ex){
            System.out.println(ex.getMessage());
        }

        return "CheckInCheckOut";
    }

    @RequestMapping(value = "/testStudentDropDown", method = RequestMethod.GET)
    public String testStudentDropDown(ModelMap modelMap){

        // known: if this page loads then no errors uncovered
        // render login user info and stashed inside json object

        // enumerates model map keys & login user info keys
        final String modelMapDataKey = "data";
        final JSONObject modelMapDataValue = new JSONObject();
        final String loginUserInfoJsonKey = "LoginUserInfo";
        final String loginUserInfoUsernameKey = "_username";
        final String loginUserInfoPasswordKey = "_password";

        // creates login user info json object equivalent
        final JSONObject loginUserInfoJson = new JSONObject();

        try{

            // sets login user info state
            loginUserInfoJson.put(loginUserInfoUsernameKey, "username");
            loginUserInfoJson.put(loginUserInfoPasswordKey, "password");

            // appends login user info json to model map json
            modelMapDataValue.put(loginUserInfoJsonKey,loginUserInfoJson);

            // appends model map json into model map
            modelMap.put(modelMapDataKey, modelMapDataValue);

        }
        catch(JSONException ex){
            System.out.println(ex.getMessage());
        }

        return "StudentDropDownMenu";
    }

    @RequestMapping(value = "/testSupervisorDropDown", method = RequestMethod.GET)
    public String testSupervisorDropDown(ModelMap modelMap){


        // known: if this page loads then no errors uncovered
        // render login user info and stashed inside json object

        // enumerates model map keys & login user info keys
        final String modelMapDataKey = "data";
        final JSONObject modelMapDataValue = new JSONObject();
        final String loginUserInfoJsonKey = "LoginUserInfo";
        final String loginUserInfoUsernameKey = "_username";
        final String loginUserInfoPasswordKey = "_password";

        // creates login user info json object equivalent
        final JSONObject loginUserInfoJson = new JSONObject();

        try{

            // sets login user info state
            loginUserInfoJson.put(loginUserInfoUsernameKey, "username");
            loginUserInfoJson.put(loginUserInfoPasswordKey, "password");

            // appends login user info json to model map json
            modelMapDataValue.put(loginUserInfoJsonKey,loginUserInfoJson);

            // appends model map json into model map
            modelMap.put(modelMapDataKey, modelMapDataValue);

        }
        catch(JSONException ex){
            System.out.println(ex.getMessage());
        }

        return "SupervisorDropDownMenu";
    }

}
