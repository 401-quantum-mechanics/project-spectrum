package com.projectspectrum.project.controllers;

import com.projectspectrum.project.models.ApplicationUser;
import com.projectspectrum.project.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

//  wire in application user database
  @Autowired
  ApplicationUserRepository applicationUserRepository;

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

  @GetMapping("/login")
  public String getLoginPage(Principal p, Model m) {

    return "login";
  }

  @GetMapping("/signup")
  public String getSignupPage(Principal p, Model m) {


    return "signup";
  }

  @GetMapping("/profile")
  public String getProfilePage(Principal p, Model m) {


    return "profile";
  }

  @GetMapping("/idea")
  public String getIdeaFormPage(Principal p, Model m) {


    return "ideaForm";
  }

  @GetMapping("/idea/details")
  public String getIdeaDetailsPage(Principal p, Model m) {


    return "ideaDetails";
  }

  @GetMapping("/ideas")
  public String getAllIdeasPage(Principal p, Model m) {


    return "allIdeas";
  }
    @GetMapping("/idea")
  public String getIdeaPage(){
    return "ideapage";
    }

}
