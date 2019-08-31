package com.projectspectrum.project.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class UserPost {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  public String idea;
  public String body;
  public Date createdAt;

  @ManyToOne
  ApplicationUser user;

  public UserPost(String idea, String body, Date createdAt, ApplicationUser user) {
    this.idea = idea;
    this.user = user;
    this.body = body;
    this.createdAt = createdAt;
  }

  public long getId() {
    return this.id;
  }



  public String getBody() {
    return this.body;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public UserPost(){}

  public String  toString(){
    return String.format("On %tD, User %s in reference to %s posted: %s", this.createdAt, this.user.username,
                         this.idea, this.body);
  }
}

