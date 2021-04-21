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



    // DUMMY method: use to test adapters for certain state
    // current test: autowired injections are not null & JPA partials rendered within Spring App
    // Context
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        modelMap.addAttribute("test", "value test here");
        // System.out.println(adapter._bagRepository.getAll() == null);

        return "index";
    }


}
