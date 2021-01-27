package com.projectspectrum.project.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class UserComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;
  public String body;
  public Date createdAt;
  public String name;

  @OneToOne
  ApplicationUser commenting_user;

  // The idea being commented on
  @ManyToOne
  UserIdea target_idea;


  public UserComment(UserIdea idea, String body, Date createdAt, ApplicationUser user) {
    this.target_idea = idea;
    this.commenting_user = user;
    this.body = body;
    this.createdAt = createdAt;
    this.name = user.firstName;
  }

  public UserComment(String body){
    this.body = body;
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

  public UserComment(){}

  public String  toString(){
    return String.format("On %tD, User %s in reference to %s posted: %s", this.createdAt, this.commenting_user.firstName,
                         this.target_idea, this.body);
  }

  public ApplicationUser getCommenting_user() {
    return commenting_user;
  }

  public UserIdea getTarget_idea() {
    return target_idea;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

