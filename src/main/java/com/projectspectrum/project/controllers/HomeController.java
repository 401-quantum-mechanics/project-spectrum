package com.projectspectrum.project.controllers;

import com.projectspectrum.project.models.ApplicationUser;
import com.projectspectrum.project.models.ApplicationUserRepository;
import com.projectspectrum.project.models.UserIdea;
import com.projectspectrum.project.models.UserIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

//  wire in application user database
  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  UserIdeaRepository userIdeaRepository;

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

  @PostMapping("/idea")
  public RedirectView createIdea(String title, String body, long createdBy, Date createAt, ApplicationUser user) {

    UserIdea newIdea = new UserIdea(title, body, createdBy, createAt, user);
    userIdeaRepository.save(newIdea);

    return new RedirectView("/profile");
  }


  @GetMapping("/idea/details")
  public String getIdeaDetailsPage(Principal p, Model m) {
    ApplicationUser applicationUser = null;
    List<UserIdea> userIdeas = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
      userIdeas = applicationUserRepository.findByUsername(p.getName()).getIdeas();

    }
    m.addAttribute("user", applicationUser);
    m.addAttribute("ideas", userIdeas);


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

  @GetMapping("/aboutus")
  public String getAboutUs(Principal p, Model m) {
    ApplicationUser applicationUser = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("user", applicationUser);
    return "aboutUs";
  }


}
