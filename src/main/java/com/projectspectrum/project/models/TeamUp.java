package com.projectspectrum.project.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TeamUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @OneToOne
    UserIdea idea;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    Set<ApplicationUser> usersOnTeam;

    public TeamUp(){}

    public TeamUp(UserIdea idea,ApplicationUser user) {
        this.idea = idea;
        this.usersOnTeam = new HashSet<>();
        this.usersOnTeam.add(user);
    }

    public long getId() {
        return id;
    }

    public UserIdea getIdea() {
        return idea;
    }

    public Set<ApplicationUser> getUsersOnTeam() {
        return usersOnTeam;
    }

    public void setUsersOnTeam(ApplicationUser userJoiningTeam) {
        this.usersOnTeam.add(userJoiningTeam);
    }
    public void removeUsersOnTeam(ApplicationUser userJoiningTeam) {
        this.usersOnTeam.remove(userJoiningTeam);
    }
}
