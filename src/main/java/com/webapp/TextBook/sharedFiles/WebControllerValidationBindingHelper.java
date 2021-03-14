package com.webapp.TextBook.sharedFiles;

import com.webapp.TextBook.validation.Shared.SharedValidationState;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotNull;

public class WebControllerValidationBindingHelper {

    public static Pair<Boolean,JSONObject> validationBindingHandler(@NotNull final BindingResult result, @NotNull final ModelMap map) throws JSONException {
        JSONObject data = new JSONObject();
        Boolean returnResult = null;
        try {


            if (result.hasErrors()) {
                //binding errors present, load in Status/Error messages and return to login
                final String failedValidationStatusMessage = "Input missing or invalid";
                if (result.getErrorCount() != 1) {
                    throw new IllegalArgumentException("Internal Error: (WebControllerValidationBindingHelper" +
                        ":validationBindingHandler) - " +
                        "View model Retaining Multiple validation Bindings. " +
                        "View model should only retain one class/type validation");
                }
                ObjectError loginUserInfoError = result.getAllErrors().get(0);


                data.put("StatusMessage", failedValidationStatusMessage);

                if (SharedValidationState.isGenericErrorMessage(loginUserInfoError.getDefaultMessage())) {
                    data.put("GeneralError", loginUserInfoError.getDefaultMessage());
                    data.put("Errors", null);
                } else {
                    data.put("GeneralError", null);
                    data.put("Errors", new JSONArray(loginUserInfoError.getDefaultMessage()).toString());

                }
                map.addAttribute("Data", data);
                returnResult = false;
            }
            else{
                returnResult = true;
            }
        }
        catch(IllegalArgumentException i){
            System.out.println();
            returnResult = false;
        }
        catch(Exception e){
            System.out.println("Internal Error: (WebControllerValidationBindingHelper" +
                    ":validationBindingHandler) - " +
                    "");
            returnResult = false;
        }
        finally {
            return new Pair<Boolean, JSONObject>(returnResult,data);
        }
    }
}
