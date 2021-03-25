package com.webapp.TextBook.validation.Shared;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public final class ErrorBinding <T> {
    private  String _fieldErrorName, _fieldErrorMessage;
    private T _errorData;
    private final static String FIELD_ERROR_NAME_KEY = "fieldName";
    private final static String FIELD_ERROR_MESSAGE_KEY = "message";
    private final static String ERROR_DATA_KEY ="faultyData";

    public  ErrorBinding(String fieldErrorName, String fieldErrorMessage, @Nullable T errorData){
        this._fieldErrorMessage = fieldErrorMessage;
        this._fieldErrorName = fieldErrorName;
        this._errorData = errorData;
    }

    public JSONObject toJsonString() throws JSONException{

        // if error name or message are not set then throw exception for invalid state in
        // error binding creation
        if(_fieldErrorName == null || _fieldErrorMessage == null){
            throw new RuntimeException("Internal Error: (ErrorBinding -- toJsonString)\n" +
                    "ErrorBindings must have a field error name and its corresponding message.\n" +
                    "Please refer to source code that created this binding in the stack trace to rectify.");

        }
        JSONObject errorBindingJsonObject = new JSONObject();

        //Attempt to create error message. if successus retunr if not see catch
        errorBindingJsonObject.put(FIELD_ERROR_NAME_KEY, _fieldErrorName);
        errorBindingJsonObject.put(FIELD_ERROR_MESSAGE_KEY, _fieldErrorMessage);
        errorBindingJsonObject.put(ERROR_DATA_KEY, _errorData); // error data can be null

        return errorBindingJsonObject;

    }


    public static class ErrorBindingJsonHelper { //Helps create JSON objects from error binding

        public static @NotNull String CreateJsonStringFromErrorBindings(@NotNull List<ErrorBinding<?>> errors) throws ErrorBindingException, Exception{
            ErrorBinding<?> faultyBinding = null;
            JSONArray jsnArr = new JSONArray();
            try{

                // getting each ErrorBinding in the array
                for (ErrorBinding<?> eb : errors){

                    //And adding it to the JSON array
                    faultyBinding = eb;

                    // uses try-catch for JSONException exceptions that occur from JSON conversion
                    // throws binding exception retaining the error binding that threw/ is invalid
                    try{
                        jsnArr.put(eb.toJsonString());
                    }
                    catch(JSONException ex){

                        // logs json exception
                        System.out.println(ex.getMessage());

                        // wraps into ErrorBindingException and rethrows
                        throw new ErrorBindingException(eb);
                    }

                }
                return  jsnArr.toString();
            }
            catch(ErrorBindingException e){
                System.out.println("Internal Error (ErrorBindingJsonHelper -- CreateJsonStringFromErrorBindings)\n" +
                        "error in converting error bindings' JSON form into string counterpart");
                throw e;
            }

        }
    }



}
