package com.scm.SCM.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.SCM.entities.Contact;
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
}
