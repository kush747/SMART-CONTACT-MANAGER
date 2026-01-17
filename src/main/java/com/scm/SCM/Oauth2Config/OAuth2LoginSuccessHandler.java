package com.scm.SCM.Oauth2Config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.SCM.entities.Providers;
import com.scm.SCM.entities.User;
import com.scm.SCM.repositories.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException{

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = token.getPrincipal();
        

        // You can access user details from oAuth2User
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");
        String providerId = oAuth2User.getAttribute("sub");

        if(email == null || providerId == null){
            // Handle error: essential information missing
            throw new IOException("Email or Provider ID not found in google data");
        }   

        // Check if user already exists
        User existingUser = userRepo.findByEmail(email).orElse(null);
        if(existingUser == null){
            // New user registration
            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setEmail(email);
            user.setName(name);
            user.setPassword(null);
            user.setEnabled(true);
            user.setProfilePic(picture);
            user.setProviderUserId(providerId);
            user.setProvider(Providers.GOOGLE);

            userRepo.save(user);


        }
        clearAuthenticationAttributes(request);
        // You can redirect or perform other actions here
        response.sendRedirect("/user/profile");
        

    }




}
