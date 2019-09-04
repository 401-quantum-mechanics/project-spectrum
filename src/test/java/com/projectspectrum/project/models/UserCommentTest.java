package com.projectspectrum.project.models;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class UserCommentTest {

  UserComment userComment = null;
  UserIdea userIdea = null;
  ApplicationUser applicationUser = null;


  @Before
  public void setUp() throws Exception {

    Date createdAt = new Date(System.currentTimeMillis());
    applicationUser =  new ApplicationUser("Bill", "Ted", "Excellent","BandT@Excellent.Adveture.com");
    userIdea = new UserIdea("What up Brah!", "Nothin, what up wich you?", createdAt, applicationUser);
    String body = "a thing to do";

    userComment = new UserComment(userIdea, body, createdAt, applicationUser);
  }

  @Test
  public void testGetId() {
    assertEquals("testGetId on array with one item equals 0.", 0, userComment.getId());
  }

  @Test
  public void testGetBody() {
    assertEquals("testGetBody equals \"a thing to do\".", "a thing to do", userComment.getBody());

  }

  @Test
  public void testGetCreatedAt() {
    assertEquals("testGetCreatedAt equals current milliseconds", new Date(System.currentTimeMillis()),
                 userComment.getCreatedAt());

  }

  @Test
  public void testGetCommenting_user() {
    assertEquals("testGetCommenting_user equals BandT@Excellent.Adveture.com", "BandT@Excellent.Adveture.com",
                 userComment.getCommenting_user().getUsername());
  }

  @Test
  public void testGetTarget_idea() {
    assertEquals("testGetTarget_idea equals.", "Project What up Brah!: Nothin, what up wich you? ; Created By: Ted, Created On 09/04/19",
                 userComment.getTarget_idea().toString());
  }
}