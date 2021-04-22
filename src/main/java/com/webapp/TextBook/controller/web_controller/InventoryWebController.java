package com.webapp.TextBook.controller.web_controller;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path ="/inventory/")
public class InventoryWebController {

    // Check in and out View


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String checkInOut(@ModelAttribute("User") LoginUserInfo user,
                             BindingResult result,
                             ModelMap map) throws JSONException {

        //verifying session user is still valid


        JSONObject data = new JSONObject();

        Pair<Boolean,JSONObject> validationResult = ValidationBindingHelper.validationBindingHandler(result,map);
        if(!validationResult.getValue0()){
            validationResult.getValue1().put("LoginUserInfo", null);
            return "login";
        }


        Triplet<Boolean, String, Optional<User>> userValidation = VerifySessionUser.isSessionUserValid(user);
        //testing verification results
        if(userValidation.getValue0()){
            // User is valid and may continue to CheckBookCopy
            data.put("LoginUserInfo", user);
            data.put("StatusMessage", userValidation.getValue1());
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("data", data);


            return "CheckInCheckOut";
        }
        else{
            //User is now invalid and must return to login

            data.put("LoginUserInfo", null);
            data.put("StatusMessage", userValidation.getValue1());
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("data", data);
            
            return "login";
        }



    }


}

