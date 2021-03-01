package com.webapp.TextBook.controller.web_controller;


import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.viewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Pair;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Controller
@RequestMapping(path = "/home/")
public class HomeController {
    // fetching shared adapter to test User input
    private static final Adapter sharedAdapter = Adapter.adapter;

    //login page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(){ return "login"; }

    //login confirmation
    @RequestMapping(value = "/loginConfirmed", method = RequestMethod.GET)
    public String loginConfirmation (
            @Valid @ModelAttribute("LoginUserInfo") LoginUserInfo person,
            BindingResult result,
            ModelMap map)  {

        // handling all exceptions with try catch block
        try {

            // format validation/dealing with binding errors
            final String failedValidationStatusMessage = "User input missing or invalid";
            if (result.hasErrors()) {
                //binding errors present, load in Status/Error messages and return to login

                assert (result.getErrorCount() != 0);
                ObjectError loginUserInfoError = result.getAllErrors().get(0);
                map.addAttribute("Error", new JSONArray(loginUserInfoError.getDefaultMessage()));
                map.addAttribute("StatusMessage", failedValidationStatusMessage);
                map.addAttribute("User", null);

                return "login";
            }

            // checking user data validity with database
            Pair<Optional<User>, StatusCode> user = sharedAdapter.userLogin(person.get_username(), person.get_password());

            //interpreting data
            if (user.getValue1() == StatusCode.OK) {
                // User login was valid and will be further processed

                map.addAttribute("Error", null);
                map.addAttribute("StatusMessage", user.getValue1().getContentMessage());
                map.addAttribute("User", user.getValue0());

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
                map.addAttribute("Error", null);
                map.addAttribute("StatusMessage", user.getValue1().getContentMessage());
                map.addAttribute("User", null);

                return "login";
            }
        }
        catch (Exception e){
            //Catching Exceptions and printing StackTrace of the error
            //Loading in custom status message and returning to login page
            System.out.println(e.getStackTrace());
            map.addAttribute("Error", null);
            map.addAttribute("StatusMessage", "Internal error has occurred. " +
                    "If this continues please contact your IT support.");
            map.addAttribute("User", null);

            return "login";
        }
    }



//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(ModelMap modelMap){
//        modelMap.addAttribute("test", "value test here");
//        System.out.println("called");
//        return "index";
//    }


}
