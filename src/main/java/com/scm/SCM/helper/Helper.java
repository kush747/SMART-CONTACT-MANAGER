package com.scm.SCM.helper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

//import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        //if login with emailid and password then fetch emailid
        // AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();
        

        if(authentication instanceof OAuth2AuthenticationToken){

            var aOuth2AuthenticationToken= (OAuth2AuthenticationToken) authentication;
            var clientid = aOuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oAuth2User =(OAuth2User) authentication.getPrincipal();
            String username="";

             //if signin with google 
            if(clientid.equals("google")){
             System.out.println("google login");
                username= oAuth2User.getAttribute("email").toString();
            }

            //if signin with github 
            else if(clientid.equals("github")){
                System.out.println("github login");
            }
            


             return username;

        }else {
            System.out.println("normal login");
            return authentication.getName();
        }

       
    }

}
