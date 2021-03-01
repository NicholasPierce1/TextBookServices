package com.webapp.TextBook.controller.web_controller;
import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
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
@RequestMapping(path ="/inventory/")
public class InventoryWebController {
    //initializing Session Verifier
    private static final VerifySessionUser verifier = new VerifySessionUser();

    // Check in and out View
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String checkInOut(@ModelAttribute("User") User user,
                             ModelMap map){

        //verifying session user is still valid
        Pair<Boolean, String> userValidation = verifier.isSessionUserValid(user);

        //testing verification results
        if(userValidation.getValue0()){
            // User is valid and may continue to CheckBookCopy

            map.addAttribute("Error", null);
            map.addAttribute("StatusMessage", userValidation.getValue1());
            map.addAttribute("User", user);

            return "CheckBookCopy";
        }
        else{
            //User is now invalid and must return to login

            map.addAttribute("Error",null);
            map.addAttribute("StatusMessage", userValidation.getValue1());
            map.addAttribute("User",null);
            
            return "login";
        }

    }
}

