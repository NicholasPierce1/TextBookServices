package com.webapp.TextBook.controller.api_controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.TextBook.repository.Bag.BagRepositoryImpl;
import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.*;
import com.webapp.TextBook.sharedFiles.StatusCode;
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
    public ResponseEntity<String> checkedoutBooks(@RequestBody String jsonString) throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        JSONObject outputData = new JSONObject();
        outputData.put("books", null);
        outputData.put("student", null);
        outputData.put("bag", null);
        outputData.put("term", null);

        try{
            JSONObject temp = new JSONObject(jsonString);
            JSONObject loginUserInfoJSON = temp.getJSONObject("loginUserInfo");
            JSONObject studentInfoJSON =temp.getJSONObject("studentInfo");

            Optional<LoginUserInfo> loginUserInfoOptional = LoginUserInfo.createApiFromJson(loginUserInfoJSON);
            Optional<StudentInfo> studentInfoOptional = StudentInfo.createApiFromJson(studentInfoJSON);

            if(loginUserInfoOptional.isEmpty() || studentInfoOptional.isEmpty()){


                outputData.put("statusMessage", null);
                outputData.put("GeneralError", generalJsonStringErrorMessage);
                outputData.put("Errors", null);
                return new ResponseEntity<String>(outputData.toString(),new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);

            }
            Optional<String> loginUserInfoValidationBindingErrorMessage =
                    apiValidationHandler.getApiBindingError(loginUserInfoOptional.get());
            Optional<String> studentInfoValidationBindingErrorMessage =
                    apiValidationHandler.getApiBindingError(studentInfoOptional.get());
            boolean loginUserErrorPresent = loginUserInfoValidationBindingErrorMessage.isPresent();
            boolean studentInfoErrorPresent =studentInfoValidationBindingErrorMessage.isPresent();

            if(loginUserErrorPresent){
                String loginUserErrorMessage = loginUserInfoValidationBindingErrorMessage.get();
                final boolean IS_GENERIC = SharedValidationState.isGenericErrorMessage(loginUserErrorMessage);
                outputData.put("statusMessage", null);
                outputData.put("GeneralError", IS_GENERIC ?  loginUserErrorMessage : null);
                outputData.put("Errors", !IS_GENERIC ?  loginUserErrorMessage : null);

            }else if(studentInfoErrorPresent){
                String studentInfoErrorMessage = studentInfoValidationBindingErrorMessage.get();
                final boolean IS_GENERIC = SharedValidationState.isGenericErrorMessage(studentInfoErrorMessage);

                outputData.put("statusMessage", null);
                outputData.put("GeneralError", IS_GENERIC ?  studentInfoErrorMessage : null);
                outputData.put("Errors", !IS_GENERIC ?  studentInfoErrorMessage : null);

            }
            else{

                LoginUserInfo loginUserInfo = loginUserInfoOptional.get();
                StudentInfo studentInfo = studentInfoOptional.get();
                Triplet<Boolean, String, Optional<User>> userValidation =
                        VerifySessionUser.isSessionUserValid(loginUserInfo);
                outputData.put("GeneralError",null);
                outputData.put("Errors",null);

                if(userValidation.getValue0()){

                    outputData.remove("books");
                    outputData.remove("student");
                    outputData.remove("bag");
                    outputData.remove("term");

                    Quintet<Optional<List<BookCopy>>, Optional<Student>, Optional<Bag>, Optional<Term>, StatusCode>
                        allCheckedOutBooks = adapter.getAllCheckedOutBooks(
                                userValidation.getValue2().get(),
                            studentInfo.getTermCode(),
                            studentInfo.getId());


                    if(allCheckedOutBooks.getValue4().equals(StatusCode.OK)) {

                        outputData.put("books", allCheckedOutBooks.getValue0().get());
                        outputData.put("student", allCheckedOutBooks.getValue1().get());
                        outputData.put("bag", allCheckedOutBooks.getValue2().get());
                        outputData.put("term", allCheckedOutBooks.getValue3().get());
                        outputData.put("statusMessage", allCheckedOutBooks.getValue4().getContentMessage());
                        return new ResponseEntity<String>(outputData.toString(), headers,
                                HttpStatus.OK);
                    }else{

                        outputData.put("books", null);
                        outputData.put("student", null);
                        outputData.put("bag", null);
                        outputData.put("term", null);
                        outputData.put("statusMessage", allCheckedOutBooks.getValue4().getContentMessage());
                        return new ResponseEntity<String>(outputData.toString(), headers,
                                HttpStatus.BAD_REQUEST);


                    }


                }else{


                    outputData.put("statusMessage", userValidation.getValue1());

                    // Might not be necessary if we want this to be bad_request status
                    return new ResponseEntity<String>(outputData.toString(), headers,
                            HttpStatus.BAD_REQUEST);

                }



            }


            return new ResponseEntity<String>(outputData.toString(), headers,
                    HttpStatus.BAD_REQUEST);

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
}
