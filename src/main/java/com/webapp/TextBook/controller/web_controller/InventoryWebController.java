package com.webapp.TextBook.controller.web_controller;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import org.javatuples.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path ="/inventory/")
public class InventoryWebController {

    // Check in and out View
    /*
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String checkInOut(@ModelAttribute("User") User user,
                             ModelMap map){

        //verifying session user is still valid
        Pair<Boolean, String> userValidation = verifier.isSessionUserValid(user);

        //testing verification results
        if(userValidation.getValue0()){
            // User is valid and may continue to CheckBookCopy

            map.addAttribute("Error", null);
            map.addAttribute("StatusMessage", userValidation.getValue1());
            map.addAttribute("User", user);

            return "CheckBookCopy";
        }
        else{
            //User is now invalid and must return to login

            map.addAttribute("Error",null);
            map.addAttribute("StatusMessage", userValidation.getValue1());
            map.addAttribute("User",null);
            
            return "login";
        }

    }

     */
}

