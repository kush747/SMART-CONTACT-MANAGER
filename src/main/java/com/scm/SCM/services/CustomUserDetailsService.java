package com.scm.SCM.services;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.scm.SCM.repositories.UserRepo;

// load user from database 

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       
       return  userRepo.findByEmail(username)
       .orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+ username));
    }

   
    

}
