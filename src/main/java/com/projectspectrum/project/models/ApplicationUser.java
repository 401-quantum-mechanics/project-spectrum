package com.projectspectrum.project.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  public long id;
  String password;
  public String username;
  public String firstName;
  public String email;
  public ArrayList ideas;
  public ArrayList teams;
  public ArrayList comments;

//  Database connections

//  Ideas
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  List<UserIdea> userIdeaList;

//  Followers and following
  @OneToMany
  @JoinTable(
    name = "user_follows",
              joinColumns = { @JoinColumn(name = "primaryUser")},
              inverseJoinColumns = {@JoinColumn(name = "followedUser")}
  )
  Set<ApplicationUser> UsersThatIFollow;

  @ManyToMany
  Set<ApplicationUser> UsersThatFollowMe;

//  Teams
  @OneToMany
  @JoinTable(
  name = "team-ups",
              joinColumns = { @JoinColumn(name = "primaryUser")},
              inverseJoinColumns = { @JoinColumn(name = "team")}
)
  Set<ApplicationUser> TeamsUsersOn;



//  class constructor
  public ApplicationUser(
    String password, String username, String firstName, String email, ArrayList ideas, ArrayList following,
    ArrayList followers, ArrayList teams, ArrayList comments
                        ) {
    this.password  = password;
    this.username  = username;
    this.firstName = firstName;
    this.email     = email;
    this.ideas     = ideas;
    this.teams     = teams;
    this.comments  = comments;
  }
//  empty constructor
  public ApplicationUser(){};

//  getters
  public long getId() {
    return this.id;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getEmail() {
    return this.email;
  }

  public ArrayList getIdeas() {
    return this.ideas;
  }

  public ArrayList getTeams() {
    return this.teams;
  }

  public ArrayList getComments() {
    return this.comments;
  }

//  security protocols
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
