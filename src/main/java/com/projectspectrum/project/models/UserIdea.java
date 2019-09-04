package com.projectspectrum.project.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
public class UserIdea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;
  public String title;
  public String body;
  public Date createAt;

@OneToOne
TeamUp team;

  @ManyToMany
  @JoinTable(
          // name is potato
          name="likes",
          // join columns: column where user like and idea
          joinColumns = { @JoinColumn(name="user_liking") },
          // inverse: column where ideas are liked
          inverseJoinColumns = { @JoinColumn(name="idea_being_liked") }
  )
  Set<ApplicationUser> liking_users;

// List of comments on the idea
@OneToMany(fetch = FetchType.EAGER, mappedBy = "target_idea")
List<UserComment> commentOnIdea;
//  Database connections
@ManyToOne
ApplicationUser user;

//@OneToMany(fetch = FetchType.EAGER, mappedBy = "idea_liked")
//Set<Like> users_liked;


//  constructor
  public UserIdea(String title, String body, Date createAt, ApplicationUser user) {
    this.title     = title;
    this.body      = body;
    this.createAt  = createAt;
    this.user = user;
    this.team = null;
  }

//  empty constructor
  public UserIdea(){};

//  Getters
  public long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getBody() {
    return this.body;
  }



  public Date getCreateAt() {
    return this.createAt;
  }

//  toString
  public String toString(){
    return String.format("Project %s: %s ; Created By: %s, Created On %tD", this.title, this.body, this.user.firstName
      , this.createAt);
  }

  public List<UserComment> getCommentOnIdea() {
    return commentOnIdea;
  }

  public ApplicationUser getUser() {
    return user;
  }


  public TeamUp getTeam() {
    return team;
  }

  public Set<ApplicationUser> getLiking_users() {
    return liking_users;
  }

  public void setTeam(TeamUp team) {
    this.team = team;
  }

  public void setCommentOnIdea(List<UserComment> commentOnIdea) {
    this.commentOnIdea = commentOnIdea;

  }
}
