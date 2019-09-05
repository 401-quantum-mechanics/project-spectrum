package com.projectspectrum.project.controllers;

import com.projectspectrum.project.models.*;
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
import java.util.Set;

@Controller
public class UserIdeaController {
    //Repos to be autowired to the controller
    @Autowired
    UserIdeaRepository userIdeaRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    UserCommentRepository userCommentRepository;

    @PostMapping("/createIdea")
    public RedirectView createIdea(String body, String title, String info,Principal user) {
        Date date = new Date(System.currentTimeMillis());
        ApplicationUser fullUser = applicationUserRepository.findByUsername(user.getName());
        UserIdea userIdea = new UserIdea(title, body, date, info,fullUser);
        userIdea.setUpTeam(fullUser);
        userIdea.setUpLiking_users(fullUser);
        userIdeaRepository.save(userIdea);
        return new RedirectView("profile");
    }

    @GetMapping("/ideas")
    public String getListOfIdeas(Model model, Principal p) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        model.addAttribute("user", applicationUser);
        List<UserIdea> ideasList = userIdeaRepository.findAll();
        model.addAttribute("ideas", ideasList);
        return "allIdeas";
    }

    @GetMapping("/ideaPage/{id}")
    public String getIdeaDetails(@PathVariable long id, Model model, Principal p) {
        UserIdea userIdea = userIdeaRepository.findById(id);
        model.addAttribute("idea_details", userIdea);

        ApplicationUser ideaUser = userIdea.getUser();
        model.addAttribute("ideaUser", ideaUser);

        Set<UserIdea> userIdeas = applicationUserRepository.findByUsername(p.getName()).getIdeas();
        model.addAttribute("ideas", userIdeas);

        Set<UserComment> comments = userIdea.getCommentOnIdea();
        model.addAttribute("comments", comments);

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        model.addAttribute("user", applicationUser);
        if (userIdea.getTeam().contains(applicationUser)) {
            model.addAttribute("team", userIdea.getTeam());
        }
        model.addAttribute("teamForSize", userIdea.getTeam());
        if (userIdea.getLiking_users().contains(applicationUser)) {
            model.addAttribute("like", userIdea.getLiking_users());
        }
        int numberOfLikes = userIdea.getLiking_users().size() - 1;
        model.addAttribute("likesForSize", numberOfLikes);


        return "ideaDetails";
    }

    @GetMapping("/teamUp/{id}")
    public RedirectView teamUp(@PathVariable long id, Principal principal, Model model) {

        UserIdea userIdea = userIdeaRepository.findById(id);

        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());

        System.out.println(user.firstName);
        if (!userIdea.getTeam().contains(user)) {
            userIdea.setTeam(user);
        } else {
            userIdea.removeTeamMate(user);
        }
        userIdeaRepository.save(userIdea);

        return new RedirectView("/ideaPage/" + id);
    }

    @PostMapping("/ideaPage/comment")
    public RedirectView createComment(String body, long ideaId, Principal p) {
        Date date = new Date(System.currentTimeMillis());
        ApplicationUser fullUser = applicationUserRepository.findByUsername(p.getName());
        UserIdea userIdea = userIdeaRepository.findById(ideaId);
        UserComment userComment = new UserComment(userIdea, body, date, fullUser);
        userCommentRepository.save(userComment);
        return new RedirectView("/profile");

    }

    @GetMapping("/likeIdea/{id}")
    public RedirectView likeIdea(@PathVariable long id, Principal principal, Model model) {

        UserIdea userIdea = userIdeaRepository.findById(id);
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        System.out.println(user.firstName);
        if (!userIdea.getLiking_users().contains(user)) {
            userIdea.setLiking_users(user);
        } else {
            userIdea.removeLike(user);
        }
        userIdeaRepository.save(userIdea);

        return new RedirectView("/ideaPage/" + id);
    }

    @PostMapping("/ideaUpdate")
    public RedirectView updatingIdea(long ideaId, String body, String title){
        UserIdea userIdea = userIdeaRepository.findById(ideaId);
        userIdea.setTitle(title);
        userIdea.setBody(body);
        userIdeaRepository.save(userIdea);
        return new RedirectView("/profile");
    }
    @PostMapping("/ideaDelete")
    public RedirectView deleteIdea(long ideaId){
        UserIdea userIdea = userIdeaRepository.findById(ideaId);
        userIdeaRepository.delete(userIdea);
        return new RedirectView("/profile");
    }
    @PostMapping("/commentUpdate")
    public RedirectView updateComment(long commentId, long ideaId, String body){
        UserComment userComment = userCommentRepository.findById(commentId);
        userComment.setBody(body);
        userCommentRepository.save(userComment);
        return new RedirectView("/ideaPage/" + ideaId);
    }
    @PostMapping("/commentDelte")
    public RedirectView deleteComment(long commentId, long ideaId){
        UserComment userComment = userCommentRepository.findById(commentId);
        userCommentRepository.delete(userComment);
        return new RedirectView("/ideaPage" + ideaId);
    }

}