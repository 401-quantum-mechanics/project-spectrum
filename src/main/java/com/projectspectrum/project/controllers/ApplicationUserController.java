package com.projectspectrum.project.controllers;

import com.projectspectrum.project.models.ApplicationUser;
import com.projectspectrum.project.models.ApplicationUserRepository;
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
import sun.jvm.hotspot.utilities.AltPlatformInfo;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

  //  wire in application user database
  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  PasswordEncoder encoder;

  @GetMapping("/login")
  public String getLoginPage() {

    return "login";
  }

  @GetMapping("/signup")
  public String getSignUp(){
    return "signup";
  }

  @PostMapping("/signup")
  public RedirectView createUser(String password, String firstName, String lastName, String email) {
    System.out.println("password " + password);
    ApplicationUser newUser = new ApplicationUser( encoder.encode(password), firstName, lastName, email);
    applicationUserRepository.save(newUser);
    Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());

    SecurityContextHolder.getContext().setAuthentication(authentication);//

    return new RedirectView("/profile");
  }

  @GetMapping("/profile")
  public String getProfile(Principal p, Model m){
    ApplicationUser applicationUser = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("user", applicationUser);
    return "profile";
  }




}
