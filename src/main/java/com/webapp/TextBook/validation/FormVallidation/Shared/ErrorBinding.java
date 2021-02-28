package com.webapp.TextBook.validation.FormVallidation.Shared;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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

    public Pair<Optional<JSONObject>, Boolean> toJsonString(){

        //if name or error message is empty return empty and false
        if(_fieldErrorName == null || _fieldErrorMessage == null){
            return new Pair<Optional<JSONObject>, Boolean>(Optional.empty(), false);

        }
        JSONObject errorBindingJsonObject = new JSONObject();
        try { //Attempt to create error message. if successus retunr if not see catch
            errorBindingJsonObject.put(FIELD_ERROR_NAME_KEY, _fieldErrorName);
            errorBindingJsonObject.put(FIELD_ERROR_MESSAGE_KEY, _fieldErrorMessage);
            errorBindingJsonObject.put(ERROR_DATA_KEY, _errorData);
            return new Pair<Optional<JSONObject>, Boolean>(Optional.ofNullable(errorBindingJsonObject), true);


        }
        catch (Exception e){ //something went wrong. Abort mission.
            return new Pair<Optional<JSONObject>, Boolean>(Optional.empty(), false);
        }



    }


    public static class ErrorBindingJsonHelper { //Helps create JSON objects from error binding

        public static <T> @NotNull String CreateJsonStringFromErrorBindings(@NotNull List<ErrorBinding<T>> errors) throws ErrorBindingException, Exception{
            ErrorBinding<T> faultyBinding = null;
            JSONArray jsnArr = new JSONArray();
            try{

                //getting each ErrorBinding in the array
                for (ErrorBinding<T> eb : errors){
                    //And adding it to the JSON array
                    faultyBinding = eb;
                    Pair<Optional<JSONObject>, Boolean> boolCheck = eb.toJsonString();
                    if(!boolCheck.getValue1() || boolCheck.getValue0().isEmpty()){ //Check if the boolean is false
                        throw new ErrorBindingException(faultyBinding);
                    }
                    jsnArr.put(boolCheck.getValue0().get().toString()); //Pulls out JSON object

                }
                return  jsnArr.toString();
            }
            catch(ErrorBindingException e){
                System.out.println("error binding json helper layer: error in converting json into string counterpart");
                throw e;
            }

        }
    }



}
