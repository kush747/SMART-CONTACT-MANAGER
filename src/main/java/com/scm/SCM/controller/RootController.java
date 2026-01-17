package com.scm.SCM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.SCM.entities.User;
import com.scm.SCM.helper.Helper;
import com.scm.SCM.services.UserService;

@ControllerAdvice
public class RootController {


    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

     @ModelAttribute
    public void initModel(Model model, Authentication authentication) {
        if(authentication==null){
            return;
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("Initializing model for user: {}", username);
        User user = userService.getUserByEmail(username);
        if(user==null){
            model.addAttribute("loggedinuser", null);
        }else{
              System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedinuser", user);

        }
      
    }



}
