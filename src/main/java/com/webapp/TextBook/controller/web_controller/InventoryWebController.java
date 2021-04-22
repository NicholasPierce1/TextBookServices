package com.webapp.TextBook.controller.web_controller;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path ="/inventory/")
public class InventoryWebController {


    @Autowired
    private ApiValidationHandler _validator;

    // Check in and out View
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String checkInOut(@RequestBody MultiValueMap<String, Object> multiValueMap,
                             ModelMap map) throws JSONException {

        // denotes the general error string to use in event of a processing error
        final String generalError = "An error occurred internally. Please contact IT Support.";

        // holds the json object for page response data
        JSONObject data = new JSONObject();

        try {
            // tries to create a login user info from the raw, form data capture
            final LoginUserInfo loginUserInfo = LoginUserInfo.createWebFromForm(multiValueMap);
            System.out.println(loginUserInfo.get_password() + " : " + loginUserInfo.get_username());

            final JSONObject validationResult = new JSONObject();

            // validates login user info and renders login page if the result is awry
            if (!ValidationBindingHelper.handleApiValidationBindingForJsonOutput(
                    this._validator.getApiBindingError(loginUserInfo),
                    validationResult
            )) {
                map.addAttribute("data", validationResult);

                return "login";
            }


            Triplet<Boolean, String, Optional<User>> userValidation = VerifySessionUser.isSessionUserValid(loginUserInfo);
            //testing verification results
            if (userValidation.getValue0()) {
                // User is valid and may continue to check in/out
                data.put("LoginUserInfo", loginUserInfo.createJsonObjectForm());
                data.put("StatusMessage", null);
                data.put("GeneralError", null);
                data.put("Errors", null);

                map.addAttribute("data", data);


                return "CheckInCheckOut";
            } else {
                //User is now invalid and must return to login

                data.put("LoginUserInfo", null);
                data.put("StatusMessage", userValidation.getValue1());
                data.put("GeneralError", null);
                data.put("Errors", null);

                map.addAttribute("data", data);

                return "login";
            }
        }
        catch(Exception ex){

            // sets error state
            data.put("LoginUserInfo", null);
            data.put("StatusMessage", generalError);
            data.put("GeneralError",null);
            data.put("Errors", null);

            map.addAttribute("data", data);

            return "login";
        }

    }


}

