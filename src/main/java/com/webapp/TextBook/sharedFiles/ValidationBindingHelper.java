package com.webapp.TextBook.sharedFiles;

import com.sun.istack.Nullable;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Helper class for any/all Error Binding validations that are used for more specific classes
 * Helps with Error handling, as well as JSON conversions
 */
public class ValidationBindingHelper {

    private static final String failedValidationStatusMessage = "Input missing or invalid";
    private static final String INTERNAL_VALIDATION_ERROR = "Internal Error: please contact IT.";

    // creates local final strings for error and general errors key
    //Used for setting standard Error keys and logic. (see line 175ish)
    private static final String ERRORS_KEY = "Errors";
    private static final String GENERAL_ERRORS_KEY = "GeneralError";

    /**
     *
     * @param result
     * @param map
     * @return Pair of boolean and JSONObject. Boolean is if there were errors, and a JSON object containing a list of errors
     * @throws JSONException
     */

    public static Pair<Boolean,JSONObject> validationBindingHandler(@NotNull final BindingResult result, @NotNull final ModelMap map) throws JSONException {
        JSONObject data = new JSONObject();
        Boolean returnResult = null;
        try {

            if (result.hasErrors()) {
                //binding errors present, load in Status/Error messages and return to login
                if (result.getErrorCount() != 1) {
                    throw new IllegalArgumentException("Internal Error: (WebControllerValidationBindingHelper" +
                        ":validationBindingHandler) - " +
                        "View model Retaining Multiple validation Bindings. " +
                        "View model should only retain one class/type validation");
                }
                ObjectError loginUserInfoError = result.getAllErrors().get(0);


                data.put("StatusMessage", failedValidationStatusMessage);

                // invoke helper to set error state
                ValidationBindingHelper.handleErrorMessageType(loginUserInfoError.getDefaultMessage(), data);

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
            data.put(GENERAL_ERRORS_KEY, INTERNAL_VALIDATION_ERROR);
            return new Pair<Boolean, JSONObject>(returnResult,data);
        }
    }

    /***
     * assist in validation execution for api validation
     * note: json exception guarantee not to occur
     * @param apiValidationResult
     * @param jsonObject
     * @return if optional is empty or not
     * @throws JSONException
     */
    public static boolean handleApiValidationBindingForJsonOutput(@NotNull final Optional<String> apiValidationResult, @NotNull final JSONObject jsonObject) throws JSONException{

        // if optional is empty then return success result, else invoke helper to set error state
        if(apiValidationResult.isEmpty()) {
           ValidationBindingHelper.handleSuccessValidationErrorMessage(jsonObject);
           return true;
        }

        ValidationBindingHelper.handleErrorMessageType(apiValidationResult.get(), jsonObject);
        return false;
    }

    /***
     * Looks to deal with errors in JSON array
     * @param apiValidationResults
     * @param jsonObject
     * @return if array is empty, then there are no errors, otherwise return false.
     * @throws JSONException
     */
    public static boolean handlerApiValidationBindingsForJsonOutput(@NotNull final List<Optional<String>> apiValidationResults, @NotNull final JSONObject jsonObject) throws JSONException{

        // enumerates local variables
        JSONArray jsonArray = new JSONArray();

        try {
            // streams list & filters on predicate that optional is not-empty (retains errors)
            //used to filter the error bindings, and then handle them
            apiValidationResults.stream().filter(
                    Optional::isPresent
            ).map(
                    Optional::get
            ).forEach(
                    (errorString) -> {

                        // captures if generic error message -- throws runtime exception if so
                        if(SharedValidationState.isGenericErrorMessage(errorString))
                            throw new RuntimeException(errorString);

                        // converts errorString into a json array
                        try {

                            // will work always
                            final JSONArray errorBindingArray = new JSONArray(errorString);

                            // checking to ensure JSONObjects are present (not empty)
                            if(errorBindingArray.length() == 0){
                                throw new JSONException("(ValidationBindingHelper): Internal Error: Empty ErrorBinding given -- ErrorStringArray");
                            }

                            // extracts all members ([JSONObject] -> JSONObject) and places into return JsonArray
                            for(int i = 0; i < errorBindingArray.length(); i++)
                                jsonArray.put(errorBindingArray.get(i)); // extracts ErrorBinding in JSONObject form
                        }
                        catch (JSONException jsonException) { // note will never occur
                            jsonException.printStackTrace();
                        }
                    }
            );

            // if json array is empty (all optionals empty --> no errors then return true)
            if(jsonArray.length() == 0){
                handleSuccessValidationErrorMessage(jsonObject);
                return true;
            }


            // set error message type
            ValidationBindingHelper.handleErrorMessageType(
                    jsonArray.toString(),
                    jsonObject
            );

            return false;
        }
        catch(JSONException jsonException){
            //todo log
            ValidationBindingHelper.handleErrorMessageType(
                    ValidationBindingHelper.INTERNAL_VALIDATION_ERROR,
                    jsonObject
            );

            return false;
        }
        catch(RuntimeException runTimeException){
            // todo log

            ValidationBindingHelper.handleErrorMessageType(
                    runTimeException.getLocalizedMessage(),
                    jsonObject
            );

            return false;
        }
    }

    /**
     *  private helper for overlapping api validation setter
     *    note: json exception guarantee not to occur
     * @param errorMessage
     * @param outputData
     * @throws JSONException
     */

    private static void handleErrorMessageType(@NotNull final String errorMessage, @NotNull final JSONObject outputData) throws JSONException{

        // captures local boolean for errorMessage state type
        final boolean IS_GENERIC = SharedValidationState.isGenericErrorMessage(errorMessage);

        // set errors state for output json object
        outputData.put(ERRORS_KEY, !IS_GENERIC ? errorMessage : null);
        outputData.put(GENERAL_ERRORS_KEY, IS_GENERIC ? errorMessage : null);

    }

    /**
     * Little helper method for dealing with general errors
     * @param outputData
     * @throws JSONException
     */
    private static void handleSuccessValidationErrorMessage(@NotNull final JSONObject outputData) throws JSONException {
        // set errors state for successful output json object
        outputData.put(ERRORS_KEY, null);
        outputData.put(GENERAL_ERRORS_KEY, null);
    }
}
