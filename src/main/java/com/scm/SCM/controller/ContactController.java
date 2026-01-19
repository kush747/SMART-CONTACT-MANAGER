package com.scm.SCM.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;
import com.scm.SCM.forms.ContactForm;
import com.scm.SCM.helper.Helper;
import com.scm.SCM.helper.Message;
import com.scm.SCM.helper.MessageType;
import com.scm.SCM.services.ContactService;
import com.scm.SCM.services.UserService;
import com.scm.SCM.services.imageService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private imageService imageService;
    //add contact page
    @RequestMapping("/add")
    public String addContactView(Model model){
         ContactForm contactForm = new ContactForm();
         
         model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult rBindingResult,HttpSession session,Authentication authentication){
        //process form data
        System.out.println("Contact Form Data: " + contactForm);

        if(rBindingResult.hasErrors()){
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        //process contact picture
        logger.info("file information :{}",contactForm.getContactImage().getOriginalFilename());
        //upload file
        String filename = UUID.randomUUID().toString();

       String fileURL = imageService.uploadImage(contactForm.getContactImage(),filename);


        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setFavourite(contactForm.isFavourite());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setUser(user);
        contact.setCloudinaryImagePublicid(filename);
        contact.setPicture(fileURL);
        
        // Here, you would typically save the contact to the database
        Contact savedContact = contactService.save(contact);
        System.out.println("Saved Contact: " + savedContact);
        

          //add message
 Message message  = Message.builder().content("Contact added successfully!!.....").type(MessageType.green).build();
 session.setAttribute("message", message);


        return "redirect:/user/contacts/add";
    }

}
