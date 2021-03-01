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
        // handle exceptions
        try {
            // format validation/ dealing with binding errors
            final String failedValidationStatusMessage = "User input missing or invalid";
            if (result.hasErrors()) {
                assert (result.getErrorCount() != 0);
                ObjectError loginUserInfoError = result.getAllErrors().get(0);
                map.addAttribute("Error", new JSONArray(loginUserInfoError.getDefaultMessage()));
                map.addAttribute("StatusMessage", failedValidationStatusMessage);
                map.addAttribute("User", null);
                return "login";
            }

            // checking user data with database
            Pair<Optional<User>, StatusCode> user = sharedAdapter.userLogin(person.get_username(), person.get_password());

            //interpreting data
            if (user.getValue1() == StatusCode.OK) {
                map.addAttribute("Error", null);
                map.addAttribute("StatusMessage", user.getValue1().getContentMessage());
                map.addAttribute("User", user.getValue0());
                //setting user session data
                SharedSessionData.setSessionValueWithKey(SharedSessionData.USER_KEY, user.getValue0().orElseThrow());

                if (user.getValue0().get().userRole == UserRole.Supervisor) {
                    return "supervisorDropdown";
                } else {
                    return "studentDropdown";
                }
            } else {
                map.addAttribute("Error", null);
                map.addAttribute("StatusMessage", user.getValue1().getContentMessage());
                map.addAttribute("User", null);
                return "login";
            }
        }
        catch (Exception e){
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
