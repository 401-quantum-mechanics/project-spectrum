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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {

  //  wire in application user database
  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  UserIdeaRepository userIdeaRepository;

  @Autowired
  PasswordEncoder encoder;

  @GetMapping("/login")
  public String getLoginPage(Principal p, Model m) {

    ApplicationUser applicationUser = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("user", applicationUser);

    return "login";
  }

  @GetMapping("/signup")
  public String getSignUp(){
    return "signup";
  }

  @PostMapping("/signup")
  public RedirectView createUser(String password, String firstName, String lastName, String username) {

    ApplicationUser newUser = new ApplicationUser( encoder.encode(password), firstName, lastName, username);

//    check database for existing username
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
    if(applicationUser!=null){
      return new RedirectView("/login");
    } else {


    applicationUserRepository.save(newUser);
    Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());

    SecurityContextHolder.getContext().setAuthentication(authentication);//

    return new RedirectView("/profile");}
  }

  @GetMapping("/profile")
  public String getProfile(Principal p, Model m){
    ApplicationUser applicationUser = null;
    List<UserIdea> userIdeas = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
      userIdeas = applicationUserRepository.findByUsername(p.getName()).getIdeas();

    }
    m.addAttribute("user", applicationUser);
    m.addAttribute("ideas", userIdeas);
    return "profile";
  }




}
