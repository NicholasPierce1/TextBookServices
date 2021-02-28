package com.webapp.TextBook.controller.web_controller;


import com.webapp.TextBook.viewModel.loginUserInfo.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping(path = "/home/")
public class HomeController {

    //login page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(){ return "login"; }

    //login confirmation
    @RequestMapping(value = "/loginConfirmed", method = RequestMethod.GET)
    public String loginConfirmation(@Valid @ModelAttribute("LoginUserInfo")LoginUserInfo u){




    }



//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(ModelMap modelMap){
//        modelMap.addAttribute("test", "value test here");
//        System.out.println("called");
//        return "index";
//    }


}
