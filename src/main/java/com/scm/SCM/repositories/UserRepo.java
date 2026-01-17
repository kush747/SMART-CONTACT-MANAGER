package com.scm.SCM.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.SCM.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, String> {

//extra methods related to db operations can be defined here

Optional<User> findByEmail(String email);


}
