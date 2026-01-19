package com.scm.SCM.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.helper.ResourceNotFoundException;
import com.scm.SCM.repositories.ContactRepo;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;


    public Contact save(Contact contact){
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    public List<Contact> getAll(){
        return contactRepo.findAll();
    }

    public Contact getById(String id){
        return contactRepo.findById(id)
          .orElseThrow(()-> new ResourceNotFoundException("Contact not found with id: " + id));

    }
    public void deleteContact(String id){
        var contact = contactRepo.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Contact not found with id: " + id));
        contactRepo.delete(contact);
    }

    public List<Contact> getContactsByUserId(String userId){
        return contactRepo.findByUserId(userId);
    }

    // update and search contact methods can be added here
}
