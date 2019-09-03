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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class UserIdeaController {
//Repos to be autowired to the controller
    @Autowired
    UserIdeaRepository userIdeaRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/createIdea")
    public RedirectView createIdea(String body, String title, Principal user){
        Date date = new Date(System.currentTimeMillis());
        ApplicationUser fullUser = applicationUserRepository.findByUsername(user.getName());
        UserIdea userIdea = new UserIdea(title,body, date, fullUser);
        userIdeaRepository.save(userIdea);
        return new RedirectView("profile");
    }

    @GetMapping("/ideas")
    public String getListOfIdeas(Model model, Principal p){
       ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
       model.addAttribute("user", applicationUser);
       List<UserIdea> ideasList =  userIdeaRepository.findAll();
       model.addAttribute("ideas", ideasList);
       return "allIdeas";
    }

    @GetMapping("/ideaPage/{id}")
    public String getIdeaDetails(@PathVariable long id, Model model, Principal p){
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        model.addAttribute("user", applicationUser);
        UserIdea userIdea = userIdeaRepository.findById(id);
        model.addAttribute("idea_details", userIdea);
        return "IdeaDetails";
    }


}
