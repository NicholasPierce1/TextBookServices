package com.webapp.TextBook.ValidationTest.Shared;

import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationImpl;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    StudentInfo valid1 = new StudentInfo("s123456", "202020");
    StudentInfo valid2 = new StudentInfo("s654321", "202010");
    StudentInfo invalid1 = new StudentInfo("2", "2");
    StudentInfo invalid2 = new StudentInfo("1", "1");


    @Test
    public void testHandlerApiValidationBindingsForJsonOutput()throws Exception{
        ArrayList<Optional<String>> listToSend = new ArrayList<Optional<String>>();
        listToSend.add(validator.getApiBindingError(valid1));
        listToSend.add(validator.getApiBindingError(valid2));
        assert (ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(listToSend, new JSONObject()));

        //Testing invalid both with just one and two
        listToSend.clear();
        listToSend.add(validator.getApiBindingError(invalid1));
        assert (ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(listToSend, new JSONObject()));
        //and the second
        listToSend.add(validator.getApiBindingError(invalid2));
        assert (ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(listToSend, new JSONObject()));


    }

}
