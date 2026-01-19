package com.scm.SCM.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 to 20 characters only")
    private String name;
     @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "phone number must contain exactly 10 digits")
    private String phoneNumber;
    @NotBlank(message = "Address cannot be empty")
    @Size(min = 5, max = 100, message = "Address must be more descriptive")
    private String address;
    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10 , max = 100, message = "write more about the contact")
    private String description;
    @Builder.Default
    private boolean favourite=false;
    private String websiteLink;
    private String linkedInLink;


    
    private MultipartFile contactImage;



}
