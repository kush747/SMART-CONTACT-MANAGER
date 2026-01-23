package com.scm.SCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scm.SCM.services.EmailService;

@Controller
@RequestMapping("/user/contacts")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value="/send_email",method=RequestMethod.POST)
    public String sendEmail(@RequestParam("to") String to,
                            @RequestParam("subject") String subject,
                            @RequestParam("message") String message
                        
){
    emailService.sendEmail(to,subject,message);
        return "redirect:/user/contacts/email";
    }

@RequestMapping("/email")
public String sendEmail(){
    return "user/email";
}

}

