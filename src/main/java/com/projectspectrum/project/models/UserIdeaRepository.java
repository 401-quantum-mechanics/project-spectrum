package com.projectspectrum.project.models;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdeaRepository extends JpaRepository<UserIdea, Long> {
  public UserIdea findById(long id);
  public UserIdea findByTitle(String title);
  public UserIdea findByCreatedBy(long id);
}
