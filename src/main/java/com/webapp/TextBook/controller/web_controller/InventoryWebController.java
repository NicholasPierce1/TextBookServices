package com.webapp.TextBook.controller.web_controller;
import com.sun.istack.Nullable;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
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

        Triplet<Boolean, String, Optional<User>> userValidation = VerifySessionUser.isSessionUserValid(user);
        JSONObject data = new JSONObject();

        final String ThereWasABindingErrorStatusMessage = "There was a Binding Error in initial WebController";

        if (result.hasErrors()){
            //binding errors present, load in Status/Error messages and return to login
            assert (result.getErrorCount() != 0);
            ObjectError loginUserInfoError = result.getAllErrors().get(0);

            data.put("LoginUserInfo", null);
            data.put("StatusMessage", ThereWasABindingErrorStatusMessage);
            if(SharedValidationState.isGenericErrorMessage(loginUserInfoError.getDefaultMessage())){
                data.put("GeneralError",loginUserInfoError.getDefaultMessage() );
                data.put("Errors", null);
            }
            else{
                data.put("GeneralError", null);
                data.put("Errors", new JSONArray(loginUserInfoError.getDefaultMessage()).toString());

            }
            map.addAttribute("Data", data);

            return "login";
        }
        //testing verification results
        if(userValidation.getValue0()){
            // User is valid and may continue to CheckBookCopy
            data.put("LoginUserInfo", user);
            data.put("StatusMessage", userValidation.getValue1());
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("Data", data);


            return "CheckBookCopy";
        }
        else{
            //User is now invalid and must return to login

            data.put("LoginUserInfo", userValidation.getValue2());
            data.put("StatusMessage", userValidation.getValue1());
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("Data", data);
            
            return "login";
        }



    }


}

