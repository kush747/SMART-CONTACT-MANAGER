package com.scm.SCM.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

// import java.util.ArrayList;
// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    private String Id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @Column(length = 100000)    
    private String description;
    private String picture;
    private boolean favourite=false;

    private String websiteLink;
    private String linkedInLink;
    // private String twitterLink;
    // private String facebookLink;
    // private String instagramLink;

    
   // private List<SocialLinks> socialLinks = new ArrayList<>();

   @ManyToOne
   private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
   private List<SocialLink> socialLinks = new ArrayList<>();







}
