package com.scm.SCM.controller;


//import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.entities.User;
import com.scm.SCM.helper.Helper;
import com.scm.SCM.services.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

   
    // user dashboard page

    @RequestMapping(value="/dashboard",method=RequestMethod.GET)
    public String userDashboard() {
        System.out.println("user dashboard page loading");
        return "user/dashboard";
    }

    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public String userProfile() {
        System.out.println("user profile page loading");
        return "user/profile" ;
    }

    //add contact page


    //view contact page


    //delete contact


    //search contact

}
