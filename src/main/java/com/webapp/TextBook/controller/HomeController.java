package com.webapp.TextBook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/view")
    public String view(boolean supervisor){
        if(!supervisor)
            return "employeeView";
        else
            return "supervisorView";
    }
}