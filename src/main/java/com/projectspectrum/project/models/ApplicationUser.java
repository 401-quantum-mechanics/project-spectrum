package com.projectspectrum.project.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
public class ApplicationUser implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  public long id;
  String password;
  public String firstName;
  public String lastName;
  public String username;


//  Database connections

//  Ideas
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  List<UserIdea> userIdeaList;
// This is many to one for users to team up
  @ManyToMany(mappedBy = "team")
  Set<UserIdea> teamUpIdeas;


//  // Comments
  @OneToOne
  UserComment comment;



//  class constructor
  public ApplicationUser(
    String password, String firstName, String lastName, String email) {
    this.password  = password;
    this.firstName  = firstName;
    this.lastName = lastName;
    this.username     = email;

  }
//  empty constructor
  public ApplicationUser(){};

//  getters
  public long getId() {
    return this.id;
  }

  public List<UserIdea> getIdeas(){
    return this.userIdeaList;
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

  public String getLastName() {
    return lastName;
  }

  public List<UserIdea> getUserIdeaList() {
    return userIdeaList;
  }


}
