package com.webapp.TextBook.validation.FormVallidation.Shared;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Optional;
import java.util.Comparator;
import java.util.ArrayList;

public final class ErrorBinding <T> {
    private  String fieldErrorName, fieldErrorMessage;
    private T errorData;
    private final static String FIELD_ERROR_NAME_KEY = "fieldName";
    private final static String FIELD_ERROR_MESSAGE_KEY = "message";
    private final  String ERROR_DATA_KEY ="faultyData";
    public  ErrorBinding(String fieldErrorMessage, String fieldErrorName,T errorData){
        this.fieldErrorMessage = fieldErrorMessage;
        this.fieldErrorName = fieldErrorName;
        this.errorData = errorData;
    }
    public Pair<Optional<JSONObject>, Boolean> toJsonString(){
        Optional<JSONObject>jsn = new Optional<JSONObject>();
        //if name or error message is empty return empty and false
        if(fieldErrorName == null || fieldErrorMessage == null){
            Pair<Optional<JSONObject>, Boolean> rtrn = new Pair<Optional<JSONObject>, Boolean>(jsn, false);

        }

    }



}
