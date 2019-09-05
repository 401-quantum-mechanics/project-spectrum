
package com.projectspectrum.project.controllers;

import com.projectspectrum.project.models.ApplicationUser;
import com.projectspectrum.project.models.ApplicationUserRepository;
import com.projectspectrum.project.models.UserIdea;
import com.projectspectrum.project.models.UserIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

  @GetMapping("/idea/details")
  public String getIdeaDetailsPage(Principal p, Model m) {
    ApplicationUser applicationUser = null;
    Set<UserIdea> userIdeas = null;

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
      userIdeas = applicationUserRepository.findByUsername(p.getName()).getIdeas();

    }
    m.addAttribute("user", applicationUser);
    m.addAttribute("ideas", userIdeas);


    return "ideaDetails";
  }


  @GetMapping("/idea/update/{id}")
  public String getIdeaUpdatePage(@PathVariable long id, Principal p, Model m) {
    ApplicationUser applicationUser = null;
    UserIdea userIdea = userIdeaRepository.findById(id);
    m.addAttribute("idea_details", userIdea);

    if(p!=null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }

    m.addAttribute("user", applicationUser);

    return "ideaUpdate";
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
