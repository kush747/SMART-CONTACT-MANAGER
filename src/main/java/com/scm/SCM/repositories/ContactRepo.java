package com.scm.SCM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.SCM.entities.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String>  {

}
