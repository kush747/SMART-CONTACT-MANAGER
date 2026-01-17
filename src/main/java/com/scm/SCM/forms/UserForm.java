package com.scm.SCM.forms;

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
public class UserForm {
    @NotBlank(message = "UserName is required")
    @Size(min = 3, max = 50, message = "UserName must be between 3 and 50 characters")
    private String name;
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be of atleast 6 characters")
    private String password;
    @NotBlank(message = "About section cannot be empty")
    @Size(max = 200, message = "About section cannot exceed 200 characters")
    private String about;
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "")
    private String phoneNumber;


}
