package com.scm.SCM.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.SCM.entities.User;
import com.scm.SCM.helper.AppConstants;
import com.scm.SCM.helper.ResourceNotFoundException;
import com.scm.SCM.repositories.UserRepo;

//import com.scm.SCM.entities.User;
@Service
public class UserService {

    @Autowired
   private UserRepo userRepo;

   @Autowired
   private PasswordEncoder passwordEncoder;

   private Logger logger = LoggerFactory.getLogger(this.getClass());


   public User saveUser(User user) {

       String userId= UUID.randomUUID().toString();
         user.setUserId(userId);
         user.setPassword(passwordEncoder.encode(user.getPassword()));

         // set user role
            user.setRoleList(List.of(AppConstants.ROLE_USER));

            logger.info(user.getProvider().toString());
         
       return userRepo.save(user);
   }

   public Optional<User> getUserById(String id) {
       return userRepo.findById(id);
   }

  public Optional<User> updateUser(User user) {
    User oldUser = userRepo.findById(user.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // update fields
    oldUser.setName(user.getName());
    oldUser.setEmail(user.getEmail());
    oldUser.setPassword(user.getPassword());
    oldUser.setPhoneNumber(user.getPhoneNumber());
    oldUser.setProfilePic(user.getProfilePic());
    oldUser.setAbout(user.getAbout());
    oldUser.setEnabled(user.isEnabled());
    oldUser.setProfilePic(user.getProfilePic());
    oldUser.setEmailVerified(user.isEmailVerified());
    oldUser.setPhoneVerified(user.isPhoneVerified());
    oldUser.setProvider(user.getProvider());
    oldUser.setProviderUserId(user.getProviderUserId());
    // ...set other fields

    User save =  userRepo.save(oldUser);
    return Optional.ofNullable(save);

}


public void deleteUser(String id){
    User oldUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    userRepo.delete(oldUser);
    
}

public boolean isUserExist(String userId){
    User oldUser = userRepo.findById(userId).orElse(null);
    return oldUser != null ? true : false;
}

public boolean isUserExistByEmail(String email){
    User oldUser = userRepo.findByEmail(email).orElse(null);
    return oldUser != null ? true : false;
}

public List<User> getAllUsers(){
    return userRepo.findAll();

}

public User getUserByEmail(String email){
    return userRepo.findByEmail(email).orElse(null);

}
}
