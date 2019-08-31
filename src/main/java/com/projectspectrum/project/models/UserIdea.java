package com.projectspectrum.project.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class UserIdea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;
  public String title;
  public String body;
  public long createdBy; // created by user Id number
  public Date createAt;

//  Database connections
@ManyToOne
ApplicationUser user;

//  constructor
  public UserIdea(String title, String body, long createdBy, Date createAt, ApplicationUser user) {
    this.title     = title;
    this.body      = body;
    this.createdBy = createdBy;
    this.createAt  = createAt;
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

  public long getCreatedBy() {
    return this.createdBy;
  }

  public Date getCreateAt() {
    return this.createAt;
  }

//  toString
  public String toString(){
    return String.format("Project %s: %s ; Created By: %s, Created On %tD", this.title, this.body, this.user.username
      , this.createAt);
  }
}
