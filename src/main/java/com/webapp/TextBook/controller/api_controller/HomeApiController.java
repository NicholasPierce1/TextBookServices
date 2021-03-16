package com.webapp.TextBook.controller.api_controller;

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
    public String checkedoutBooks(@RequestBody String jsonString){

        try{



            return "";

        }catch(Exception e){

            return "";
        }




    }
}
