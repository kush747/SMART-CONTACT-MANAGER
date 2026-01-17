package com.scm.SCM.Oauth2Config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(OAuth2SuccessHandler.class);


    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
         HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {

            logger.info("OAuthAuthenticationSuccessHandler");

           var oauth2AuthenticationToken =  (OAuth2AuthenticationToken)authentication.getPrincipal();
           String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
           logger.info(authorizedClientRegistrationId);

           if(authorizedClientRegistrationId.equalsIgnoreCase("GOOGLE")){
            


           }
           else if(authorizedClientRegistrationId.equalsIgnoreCase("GITHUB")){

           }

       
        throw new UnsupportedOperationException("Unimplemented methAod 'onAuthenticationSuccess'");
    }
    
}