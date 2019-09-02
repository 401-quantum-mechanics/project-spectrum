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

    return "homepage";
  }
}
