package com.webapp.TextBook.controller.web_controller;


import javassist.compiler.ast.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import org.springframework.http.MediaType;

import javax.sound.midi.SysexMessage;
import javax.validation.Valid;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @RequestMapping(value = {"/{toReturn}", "/"}, method = RequestMethod.GET)
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
