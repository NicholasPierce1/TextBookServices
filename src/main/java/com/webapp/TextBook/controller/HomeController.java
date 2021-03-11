package com.webapp.TextBook.controller;

import java.util.List;

import com.webapp.TextBook.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String login(){
        return "login";
    }


}