package com.webapp.TextBook.controller.web_controller;


import javassist.compiler.ast.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import org.springframework.http.MediaType;

import javax.sound.midi.SysexMessage;
import javax.validation.Valid;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        modelMap.addAttribute("test", "value test here");


        final JSONObject data = new JSONObject();
        try {
            data.put("GeneralErrors", null);
            data.put("Errors", null);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }

        modelMap.addAttribute("data", data);

        System.out.println("called");
        return "login";
    }

    @RequestMapping(value = "/testCheckInOut", method = RequestMethod.GET)
    public String testCheckInOut(){
        return "CheckInCheckOut";
    }


}
