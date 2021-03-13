package com.webapp.TextBook.controller.web_controller;


import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.*;


import javax.validation.Valid;

import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;

@Controller
@RequestMapping(path = "/home/")
public class HomeController {


    // fetching shared adapter for encapsulated, database interaction
    @Autowired Adapter adapter;

    //login page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(){ return "login"; }

    //login confirmation
    @RequestMapping(value = "/loginConfirmed", method = RequestMethod.GET)
    public String loginConfirmation (
            @Valid @ModelAttribute("LoginUserInfo") LoginUserInfo person,
            BindingResult result,
            ModelMap map) throws JSONException {
        JSONObject data = new JSONObject();
        // handling all exceptions with try catch block
        try {

            // format validation/dealing with binding errors
            final String failedValidationStatusMessage = "User input missing or invalid";
            if (result.hasErrors()) {
                //binding errors present, load in Status/Error messages and return to login

                assert (result.getErrorCount() != 0);
                ObjectError loginUserInfoError = result.getAllErrors().get(0);

                data.put("LoginUserInfo", null);
                data.put("StatusMessage", failedValidationStatusMessage);

                if(SharedValidationState.isGenericErrorMessage(loginUserInfoError.getDefaultMessage())){
                    data.put("GeneralError",loginUserInfoError.getDefaultMessage() );
                    data.put("Errors", null);
                }
                else{
                    data.put("GeneralError", null);
                    data.put("Errors", new JSONArray(loginUserInfoError.getDefaultMessage()).toString());

                }
                map.addAttribute("Data", data);
                /*
                map.addAttribute("Error", new JSONArray(loginUserInfoError.getDefaultMessage()));
                map.addAttribute("StatusMessage", failedValidationStatusMessage);
                map.addAttribute("User", null);
                */
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
                /*
                map.addAttribute("Error", null);
                map.addAttribute("StatusMessage", user.getValue1().getContentMessage());
                map.addAttribute("User", person);
                */
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
                /*
                map.addAttribute("Error", null);
                map.addAttribute("StatusMessage", user.getValue1().getContentMessage());
                map.addAttribute("User", null);
                */
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
            /*
            map.addAttribute("Error", null);
            map.addAttribute("StatusMessage", "Internal error has occurred. " +
                    "If this continues please contact your IT support.");
            map.addAttribute("User", null);
            */
            return "login";
        }
    }



    // DUMMY method: use to test adapters for certain state
    // current test: autowired injections are not null & JPA partials rendered within Spring App
    // Context
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        modelMap.addAttribute("test", "value test here");
        System.out.println(adapter._bagRepository.getAll() == null);

        return "index";
    }


}
