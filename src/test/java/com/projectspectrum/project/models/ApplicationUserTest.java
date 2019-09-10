package com.projectspectrum.project.models;

import org.junit.Before;
import org.junit.Test;

import java.time.chrono.JapaneseChronology;

import static org.junit.Assert.*;

public class ApplicationUserTest {

  ApplicationUser applicationUser = null;

  @Before
  public void setUp() throws Exception {
       applicationUser =  new ApplicationUser("Bill", "Ted", "Excellent","BandT@Excellent.Adveture.com");
  }

  @Test
  public void testGetId() {
    // array with one item? this seems like a leftover test message :(
    assertEquals("testGetId on array with one item equals 0.", 0, applicationUser.getId());
  }

  @Test
  public void testGetPassword() {
    assertEquals("testGetPassword should equal Bill.", "Bill", applicationUser.getPassword());
  }

  @Test
  public void testGetUsername() {
    assertEquals("testGetUsername should equal BandT@Excellent.Adveture.com.", "BandT@Excellent.Adveture.com", applicationUser.getUsername());
  }

  @Test
  public void testGetFirstName() {
    assertEquals("testGetFirstName should equal Ted.", "Ted", applicationUser.getFirstName());
  }

  @Test
  public void testGetLastName() {
    assertEquals("testGetLastName should equal Excellent.", "Excellent", applicationUser.getLastName());
  }

}