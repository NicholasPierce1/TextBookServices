package com.webapp.TextBook.validation.FormVallidation.Shared;

import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public abstract class ErrorBindingJsonHelper { //Helps create JSON objects from error binding

    public @NotNull String CreateJsonStringFromErrorBindings(@NotNull List<ErrorBinding> errors) throws ErrorBindingException, Exception{
        ErrorBinding faultyBinding = null;
        JSONArray jsnArr = new JSONArray();
        try{

            //getting each ErrorBinding in the array
            for (ErrorBinding eb : errors){
                //And adding it to the JSON array
                faultyBinding = eb;
                Pair<Optional<JSONObject>, Boolean> boolCheck = eb.toJsonString();
                if(!boolCheck.getValue1()){ //Check if the boolean is fale
                    throw  new ErrorBindingException(faultyBinding);
                }
                jsnArr.put(faultyBinding.toJsonString().getValue0()); //Pulls out JSON object

            }
            return  jsnArr.toString();
        }

        catch (Exception e){
            throw  e;
        }
    }
}
