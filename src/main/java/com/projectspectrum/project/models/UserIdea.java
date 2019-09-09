package com.projectspectrum.project.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserIdea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;
  public String title;
  public String body;
  public String info;
  public Date createAt;

  @ManyToMany
  @JoinTable(
          // name is potato
          name="team_up",
          // join columns: column where ideas are teamed up on
          joinColumns = { @JoinColumn(name="idea_id") },
          // inverse: column where user join team on an idea
          inverseJoinColumns = { @JoinColumn(name="user_joined_id") }
  )
  Set<ApplicationUser> team;

  @ManyToMany
  @JoinTable(
          // name is potato
          name="likes",
          // join columns column where ideas are liked
          joinColumns = { @JoinColumn(name="idea_id") },
          // inverse: column where user like and idea
          inverseJoinColumns = { @JoinColumn(name="user_liking_idea_id") }
  )
  Set<ApplicationUser> liking_users;

// List of comments on the idea
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "target_idea")
  Set<UserComment> commentOnIdea;

  @ManyToOne
  ApplicationUser user;

//  constructor
  public UserIdea(String title, String body, Date createAt,String info, ApplicationUser user) {
    this.title = title;
    this.body = body;
    this.createAt = createAt;
    this.info = info;
    this.user = user;
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

  public Set<UserComment> getCommentOnIdea() {
    return commentOnIdea;
  }

  public ApplicationUser getUser() {
    return user;
  }

  public Set<ApplicationUser> getLiking_users() {
    return liking_users;
  }

  public Set<ApplicationUser> getTeam() {
    return team;
  }

  public void setUpTeam(ApplicationUser user) {
    this.team = new HashSet<>();
    this.team.add(user);
  }

  public void setTeam(ApplicationUser user) {
    this.team.add(user);
  }

  public void removeTeamMate(ApplicationUser user) {
    this.team.remove(user);
  }

  public void setUpLiking_users(ApplicationUser user) {
    this.liking_users = new HashSet<>();
    this.liking_users.add(user);
  }
  public void setLiking_users(ApplicationUser user) {
    this.liking_users.add(user);
  }

  public void removeLike(ApplicationUser user) {
    this.liking_users.remove(user);
  }

  public void setUser(ApplicationUser user) {
    this.user = user;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setBody(String body) {
    this.body = body;
  }

  //User for when Idea is updated to display updated at
  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
