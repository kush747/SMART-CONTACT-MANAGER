package com.scm.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.forms.ContactForm;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    

    //add contact page
    @RequestMapping("/add")
    public String addContactView(Model model){
         ContactForm contactForm = new ContactForm();
         
         model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm){
        //process form data
        System.out.println("Contact Form Data: " + contactForm);

        return "redirect:/user/contacts/add";
    }

}
