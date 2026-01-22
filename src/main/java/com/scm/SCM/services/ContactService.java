package com.scm.SCM.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;
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

    public List<Contact> getByUserId(String userId){
        return contactRepo.findByUserId(userId);
    }

    public Page<Contact> getContactsByUser(User user,int page, int size,String sortBy, String direction){

        Sort sort = direction.equals("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size,sort);

        
        return contactRepo.findByUser(user, pageable);
    }

    public Page<Contact> searchByName(String nameKeyword,int size,int page,String sortBy,String direction){
        Sort sort = direction.equals("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);

     return contactRepo.findByNameContaining(nameKeyword, pageable);

    }
    public Page<Contact> searchByEmail(String emailKeyword,int size,int page,String sortBy,String direction){
         Sort sort = direction.equals("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByEmailContaining(emailKeyword, pageable);

    }
    public Page<Contact> searchByNumber(String phoneNumberKeyword,int size,int page,String sortBy,String direction){
         Sort sort = direction.equals("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByPhoneNumberContaining(phoneNumberKeyword, pageable);

    }

    // update and search contact methods can be added here

    public Contact update(Contact contact){

     var oldContact= contactRepo.findById(contact.getId()).orElseThrow(() -> new ResourceNotFoundException("contact not found"));
     oldContact.setName(contact.getName());
     oldContact.setEmail(contact.getEmail());
     oldContact.setPhoneNumber(contact.getPhoneNumber());
     oldContact.setAddress(contact.getAddress());
     oldContact.setDescription(contact.getDescription());
     oldContact.setLinkedInLink(contact.getLinkedInLink());
     oldContact.setWebsiteLink(contact.getWebsiteLink());
     oldContact.setFavourite(contact.isFavourite());
     oldContact.setPicture(contact.getPicture());
     oldContact.setCloudinaryImagePublicid(contact.getCloudinaryImagePublicid());
   
        return contactRepo.save(oldContact);

    }
}
