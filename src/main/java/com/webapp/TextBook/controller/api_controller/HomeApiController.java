package com.webapp.TextBook.controller.api_controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.TextBook.repository.Bag.BagRepositoryImpl;
import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.*;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.sharedFiles.ValidationBindingHelper;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.apache.tomcat.util.json.JSONParser;
import org.javatuples.Quintet;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/rest/api/inventory")
public class HomeApiController {
    final static String generalJsonStringErrorMessage = "(HomeAPIController) - Invalid JsonString passed into controller";

    @Autowired
    private ApiValidationHandler apiValidationHandler;

    @Autowired
    Adapter adapter;

    @RequestMapping(value = "/getCheckedOutBooks", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCheckedOutBooks(@RequestBody String jsonString) throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        JSONObject outputData = new JSONObject();
        outputData.put("books", null);
        outputData.put("student", null);
        outputData.put("bag", null);
        outputData.put("term", null);
        outputData.put("statusMessage", null);

        try{

            JSONObject temp = new JSONObject(jsonString);


            Optional<LoginUserInfo> loginUserInfoOptional = LoginUserInfo.createApiFromJson(temp.getJSONObject("loginUserInfo"));
            Optional<StudentInfo> studentInfoOptional = StudentInfo.createApiFromJson(temp.getJSONObject("studentInfo"));

            if(loginUserInfoOptional.isEmpty() || studentInfoOptional.isEmpty()){


                outputData.put("statusMessage", null);
                outputData.put("GeneralError", generalJsonStringErrorMessage);
                outputData.put("Errors", null);
                return new ResponseEntity<String>(outputData.toString(),new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);

            }

            ArrayList<Optional<String>> apiValidationResultList = new ArrayList<Optional<String>>();

            apiValidationResultList.add(apiValidationHandler.getApiBindingError(loginUserInfoOptional.get()));
            apiValidationResultList.add(apiValidationHandler.getApiBindingError(studentInfoOptional.get()));

            if(!ValidationBindingHelper.handlerApiValidationBindingsForJsonOutput(apiValidationResultList, outputData))
                // error or errors found and incorporated into json object output
                return new ResponseEntity<String>(
                        outputData.toString(),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST
                );



                LoginUserInfo loginUserInfo = loginUserInfoOptional.get();
                StudentInfo studentInfo = studentInfoOptional.get();
                Triplet<Boolean, String, Optional<User>> userValidation =
                        VerifySessionUser.isSessionUserValid(loginUserInfo);

                if(!userValidation.getValue0()){

                    // verify session user is general error not status message
                    outputData.put("GeneralError", userValidation.getValue1());

                    // Might not be necessary if we want this to be bad_request status
                    return new ResponseEntity<String>(
                            outputData.toString(),
                            headers,
                            HttpStatus.BAD_REQUEST
                    );
                }

                    Quintet<Optional<List<BookCopy>>, Optional<Student>, Optional<Bag>, Optional<Term>, StatusCode>
                            allCheckedOutBooks = adapter.getAllCheckedOutBooks(
                            userValidation.getValue2().orElseThrow(),
                            studentInfo.getTermCode(),
                            studentInfo.getId());

                    if(allCheckedOutBooks == null) // should never happen
                        throw new RuntimeException("adapter return null -- check logs on inputs for internal nested errors");

                    // invokes clobber, or mask behavior in Map types, on overlapping key values
                    outputData.put("books", allCheckedOutBooks.getValue0().orElseGet(() -> null));
                    outputData.put("student",  allCheckedOutBooks.getValue1().orElseGet(() -> null));
                    outputData.put("bag",  allCheckedOutBooks.getValue2().orElseGet(() -> null));
                    outputData.put("term",  allCheckedOutBooks.getValue3().orElseGet(() -> null));
                    outputData.put("statusMessage",  allCheckedOutBooks.getValue4());

                    return new ResponseEntity<String>(
                            outputData.toString(),
                            headers,
                            HttpStatus.OK
                    );


        }catch(JSONException e){


            outputData.put("statusMessage", null);
            outputData.put("GeneralError", generalJsonStringErrorMessage);
            outputData.put("Errors", null);

            return  new ResponseEntity<String>(outputData.toString(), headers,
                    HttpStatus.BAD_REQUEST);
        }catch(Exception e){

            outputData.put("statusMessage", null);
            outputData.put("GeneralError", "Internal Error: (HomeApiController)");
            outputData.put("Errors", null);
            return new ResponseEntity<String>(outputData.toString(), headers,
                    HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/checkoutBook/", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkOutBook(@RequestBody String jsonString) throws JSONException{
        HttpHeaders headers = new HttpHeaders();
        JSONObject outputData = new JSONObject();
        outputData.put("bookCopy",null);
        outputData.put("statusMessage", null);


        

        return new ResponseEntity<String>(outputData.toString(), headers,
                HttpStatus.BAD_REQUEST);


    }
    // private helper to handler api error state
}
