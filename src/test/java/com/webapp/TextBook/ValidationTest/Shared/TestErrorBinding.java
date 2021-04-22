package com.webapp.TextBook.ValidationTest.Shared;

import com.sun.istack.Nullable;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class tests Error Binding
 */
public class TestErrorBinding <T>{

    private final static String FIELD_ERROR_NAME_KEY = "fieldName";
    private final static String FIELD_ERROR_MESSAGE_KEY = "message";
    private final static String ERROR_DATA_KEY ="faultyData";
    //PRE TEST: Create three error bindings
    @Test
    public void testJsonToString() throws  Exception{
        //PRE TEST: Create three error bindings
        //Testing string

        assert (assertTrue("String test", (T) "My String", false));
        ErrorBinding intTest = new ErrorBinding("Int test", "Int", 22);
        //Testing int
        Integer i = 22;
        assert(assertTrue("Int test", (T) i, false));
        //Testing double
        Double d = 4.4;
        assert(assertTrue("Double Test", (T)d, false));
        //Testing bad result
        assert(assertTrue(null, null, true));

    }

/**
 * Helper method to speed up testing
 */
    private boolean assertTrue(String msg, @Nullable T errorData, boolean shouldFail) throws Exception {
        String test = "test";
        try{
            //Create test objects
            ErrorBinding errBinding = new ErrorBinding(test, msg, errorData);
            //Creating the JSON for test
            JSONObject expected = new JSONObject();
            expected.put(FIELD_ERROR_NAME_KEY, test);
            expected.put(FIELD_ERROR_MESSAGE_KEY, msg);
            expected.put(ERROR_DATA_KEY, errorData);
            JSONObject  strObj = errBinding.toJsonString();
            return (expected.toString().equals(strObj.toString()));



        }
        //Catches errors
        catch (Exception e){
            String errMsgExpected = "Internal Error: (ErrorBinding -- toJsonString)\n" +
                    "ErrorBindings must have a field error name and its corresponding message.\n" +
                    "Please refer to source code that created this binding in the stack trace to rectify.";
            if (e.getMessage().equals(errMsgExpected)){
                //If we expected it to fail and the msg is correct, return if it was supposed to be an error or not
                return shouldFail;
            }

            else{
                throw new RuntimeException("ERROR IN TEST ERROR BINDING: Unexpceted error\n " + e.getStackTrace());
            }
        }

    }



}
