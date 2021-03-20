package com.webapp.TextBook.controller.api_controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/rest/api/inventory")
public class HomeApiController {
    final static String generalJsonStringErrorMessage = "(HomeAPIController) - Invalid JsonString passed into controller";
    @RequestMapping(value = "/getCheckedOutBooks", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkedoutBooks(@RequestBody String jsonString) throws JSONException {

        try{
            JSONObject temp = new JSONObject(jsonString);
            JSONObject loginUser = new JSONObject(temp.getString("loginUserInfo"));
            JSONObject studentInfo = new JSONObject(temp.getString("studentInfo"));



            return "";

        }catch(JSONException e){
            JSONObject outputData = new JSONObject();
            outputData.put("books", null);
            outputData.put("student", null);
            outputData.put("bag", null);
            outputData.put("term", null);
            outputData.put("statusMessage", null);
            outputData.put("GeneralError", generalJsonStringErrorMessage);
            outputData.put("Errors", null);

            return  "";
        }catch(Exception e){

            return "";
        }




    }
}
