package com.projectspectrum.project.controllers;

import com.projectspectrum.project.models.ApplicationUser;
import com.projectspectrum.project.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

//  wire in application user database
  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  PasswordEncoder encoder;

//  Root directory access permission all
  @GetMapping("/")
  public String getRoot(Principal p, Model m){

    ApplicationUser applicationUser = null;
    if(p != null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }

    m.addAttribute("user", applicationUser);

    return "home";
  }

  @GetMapping("/idea")
  public String getIdeaFormPage(Principal p, Model m) {
    ApplicationUser applicationUser = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("user", applicationUser);


    return "ideaForm";
  }

  @GetMapping("/idea/details")
  public String getIdeaDetailsPage(Principal p, Model m) {
    ApplicationUser applicationUser = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("user", applicationUser);


    return "ideaDetails";
  }


  @GetMapping("/ideas")
  public String getAllIdeasPage(Principal p, Model m) {
    ApplicationUser applicationUser = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("user", applicationUser);


    return "allIdeas";
  }

}
