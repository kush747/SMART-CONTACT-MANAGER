package com.scm.SCM.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.entities.User;
//import com.scm.SCM.entities.User;
import com.scm.SCM.forms.UserForm;
import com.scm.SCM.helper.Message;
import com.scm.SCM.helper.MessageType;
import com.scm.SCM.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller
public class PageController {
    @Autowired
    private UserService userService;

@RequestMapping("/home")
public String home(Model model) {
System.out.println("Inside home controller");
model.addAttribute("name","Kola");
model.addAttribute("id","100000002");
model.addAttribute("email","k@gmail.com");

    return "home";
}


// about
@RequestMapping("/about")
public String about() {
    System.out.println("about page loading");
    return "about";
}


//services
@RequestMapping("/services")
public String services(){
    System.out.println("services page loading");
    return "services";
}

//contacts (support both /contact and /contacts - header uses /contact)
@GetMapping({"/contact", "/contacts"})
public String contact() {
    System.out.println("contact page loading");
    return "contact";
}
@GetMapping("/login")
public String login() {
    return new String("login");
}
@GetMapping("/signup")
public String signup(Model model) {

  UserForm userForm = new UserForm();

  model.addAttribute("userForm", userForm);
    return "signup";

}



//processing register
@RequestMapping(value = "/do-register", method = RequestMethod.POST)
public String processRegister (@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,HttpSession session) {
System.out.println("processing register form");
//fetch form data validate then save to databse then message registraction success then redirect to login page
System.out.println(userForm);
//fetch data

// validation of data
if(rBindingResult.hasErrors()){
    return "signup";
}


//save to database
// User user = User.builder().
// name(userForm.getName())
// .email(userForm.getEmail())
// .password(userForm.getPassword())
// .phoneNumber(userForm.getPhoneNumber())
// .about(userForm.getAbout())
// .build();

User user = new User();
user.setName(userForm.getName());
user.setEmail(userForm.getEmail());
user.setPassword(userForm.getPassword());
user.setPhoneNumber(userForm.getPhoneNumber());
user.setAbout(userForm.getAbout());
user.setProfilePic("");



  User savedUser = userService.saveUser(user);
  System.out.println("saved user"+ savedUser);

  //add message
 Message message  = Message.builder().content("Registrationn succesfull!!.....").type(MessageType.green).build();
 session.setAttribute("message", message);


    return "redirect:/signup";
}

}
