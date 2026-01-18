package com.scm.SCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.forms.ContactForm;
import com.scm.SCM.helper.Message;
import com.scm.SCM.helper.MessageType;
import com.scm.SCM.services.ContactService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    //add contact page
    @RequestMapping("/add")
    public String addContactView(Model model){
         ContactForm contactForm = new ContactForm();
         
         model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult rBindingResult,HttpSession session){
        //process form data
        System.out.println("Contact Form Data: " + contactForm);

        if(rBindingResult.hasErrors()){
            return "user/add_contact";
        }

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setFavourite(contactForm.isFavourite());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        
        // Here, you would typically save the contact to the database
        Contact savedContact = contactService.save(contact);
        System.out.println("Saved Contact: " + savedContact);

          //add message
 Message message  = Message.builder().content("Contact added successfully!!.....").type(MessageType.green).build();
 session.setAttribute("message", message);


        return "redirect:/user/contacts/add";
    }
}
