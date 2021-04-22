package com.webapp.TextBook.ValidationTest.Shared;

import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationImpl;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Class tests Validation Binding helper. Looking specifically to get some validation
 */
@SpringBootTest
public class TestValidationBindingHelper {

    @Autowired
    private ApiValidationHandler validator;

    //Creating Valid viewModel objects
    StudentInfo valid1 = new StudentInfo("919123456", "202020");
    StudentInfo valid2 = new StudentInfo("919123789", "202010");
    StudentInfo invalid1 = new StudentInfo("2", "2");
    StudentInfo invalid2 = new StudentInfo("1", "1");


    @Test
    public void testHandlerApiValidationBindingsForJsonOutput()throws Exception{

        final String errorsKey = "Errors";
        final String generalErrorsKey= "GeneralError";

        ArrayList<Optional<String>> listToSend = new ArrayList<Optional<String>>();
        listToSend.add(validator.getApiBindingError(valid1));
        listToSend.add(validator.getApiBindingError(valid2));
        final JSONObject jsonObjectInputGood = new JSONObject();
        final JSONObject jsonObjectOutputGood =  new JSONObject();
        jsonObjectOutputGood.put(errorsKey, null);
        jsonObjectOutputGood.put(generalErrorsKey, null);
        assert (ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(listToSend, jsonObjectInputGood));
        assert(jsonObjectInputGood.toString().equals(jsonObjectOutputGood.toString()));

        //Testing invalid both with just one and two
        listToSend.clear();
        listToSend.add(validator.getApiBindingError(invalid1));

        final JSONObject jsonObjectInputBadOne = new JSONObject();
        final JSONObject jsonObjectOutputBadOne =  new JSONObject();

        final JSONArray faultyConglomerateJsonArray = new JSONArray();

        final JSONArray faultyBindingArrayOne = new JSONArray(validator.getApiBindingError(invalid1).orElseThrow());

        for(int i = 0; i < faultyBindingArrayOne.length(); i++)
            faultyConglomerateJsonArray.put(faultyBindingArrayOne.getJSONObject(i));

        jsonObjectOutputBadOne.put(errorsKey, faultyConglomerateJsonArray.toString());
        jsonObjectOutputBadOne.put(generalErrorsKey, null);

        assert (!ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(listToSend, jsonObjectInputBadOne));
        assert(jsonObjectInputBadOne.toString().equals(jsonObjectOutputBadOne.toString()));
        //and the second
        listToSend.add(validator.getApiBindingError(invalid2));

        final JSONObject jsonObjectInputBadTwo = new JSONObject();
        final JSONObject jsonObjectOutputBadTwo =  new JSONObject();

        final JSONArray faultyBindingArrayTwo = new JSONArray(validator.getApiBindingError(invalid2).orElseThrow());

        for(int i = 0; i < faultyBindingArrayTwo.length(); i++)
            faultyConglomerateJsonArray.put(faultyBindingArrayTwo.getJSONObject(i));

        jsonObjectOutputBadTwo.put(errorsKey, faultyConglomerateJsonArray.toString());
        jsonObjectOutputBadTwo.put(generalErrorsKey, null);

        assert (!ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(listToSend, jsonObjectInputBadTwo));
        assert(jsonObjectInputBadTwo.toString().equals(jsonObjectOutputBadTwo.toString()));

    }

}