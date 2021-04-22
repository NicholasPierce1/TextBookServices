package com.webapp.TextBook.controller.web_controller;


import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


import javax.validation.Valid;

import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;

@Controller
@RequestMapping(path = "/home/")
public class HomeController {


    @RequestMapping(value = {"/testLogin/{toReturn}", "/"}, method = RequestMethod.GET)
    public String index(@PathVariable(required = false, name = "toReturn") Optional<String> toReturn, ModelMap modelMap){
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
                        data.put("Errors", errorBindingArray);
                        break;
                    case 2:
                        errorBindingArray.put(usernameErrorBinding);
                        errorBindingArray.put(passwordErrorBinding);
                        data.put("Errors", errorBindingArray);
                        break;
                    case 3:
                        System.out.print("3 called");
                        data.put("GeneralErrors", generalErrorsValue);

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
    public String login(){ return "login"; }

    //login confirmation
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginConfirmation (
            @Valid @ModelAttribute("LoginUserInfo") LoginUserInfo person,
            BindingResult result,
            ModelMap map) throws JSONException {
        JSONObject data = new JSONObject();
        // handling all exceptions with try catch block
        try {

            // format validation/dealing with binding errors
            Pair<Boolean,JSONObject> validationResult = ValidationBindingHelper.validationBindingHandler(result,map);
            if(!validationResult.getValue0()){
                validationResult.getValue1().put("LoginUserInfo", null);
                return "login";
            }

            // checking user data validity with database
            Pair<Optional<User>, StatusCode> user = adapter.userLogin(person.get_username(), person.get_password());

            //interpreting data
            if (user.getValue1() == StatusCode.OK) {
                // User login was valid and will be further processed
                data.put("LoginUserInfo", person);
                data.put("StatusMessage", user.getValue1().getContentMessage());
                data.put("GeneralError",null);
                data.put("Errors", null);

                map.addAttribute("Data", data);

                //setting user session data
                SharedSessionData.setSessionValueWithKey(SharedSessionData.USER_KEY, user.getValue0().orElseThrow());

                //checking user Role
                if (user.getValue0().get().userRole == UserRole.Supervisor) {
                    // User Role is Supervisor and will continue as such
                    return "supervisorDropdown";
                } else {
                    // User Role is Student Employee and will continue as such
                    return "studentDropdown";
                }
            } else {
                // User login was invalid for reason stated in status message
                // return to login
                data.put("LoginUserInfo", null);
                data.put("StatusMessage", user.getValue1().getContentMessage());
                data.put("GeneralError",null);
                data.put("Errors", null);

                map.addAttribute("Data", data);

                return "login";
            }
        }
        catch (Exception e){
            //Catching Exceptions and printing StackTrace of the error
            //Loading in custom status message and returning to login page
            System.out.println(e.getStackTrace());
            data.put("LoginUserInfo", null);
            data.put("StatusMessage", "Internal error has occurred. " +
                    "If this continues please contact your IT support.");
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("Data", data);

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
