package com.projectspectrum.project.models;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class UserIdeaTest {

  UserComment userComment = null;
  UserIdea userIdea = null;
  ApplicationUser applicationUser = null;

  @Before
  public void setUp() throws Exception {

    Date createdAt = new Date(System.currentTimeMillis());
    applicationUser =  new ApplicationUser("Bill", "Ted", "Excellent","BandT@Excellent.Adveture.com");
    new UserIdea();
    userIdea = new UserIdea("What up Brah!", "Nothin, what up wich you?", createdAt, "Not much", applicationUser);

  }

  @Test
  public void testGetId() {
    assertEquals("testGetId equals 0", 0, userIdea.getId());
  }

  @Test
  public void testGetTitle() {
    System.out.println(userIdea.getTitle());
    assertEquals("testGetTitle equals", "What up Brah!", userIdea.getTitle());
  }

  @Test
  public void testGetBody() {
    assertEquals("testGetBody equals", "Nothin, what up wich you?", userIdea.getBody());
  }

  @Test
  public void testGetCreateAt() {
    // if this test happens to be run right at midnight, this will fail!
    // better to save now in a variable during the @before for testing purposes.
    assertEquals("testGetCreateAt equals", java.time.LocalDate.now().toString(), userIdea.getCreateAt().toString());
  }

  @Test
  public void testGetUser() {
    assertEquals("testGetUser equals", "BandT@Excellent.Adveture.com",userIdea.getUser().getUsername());
  }

}