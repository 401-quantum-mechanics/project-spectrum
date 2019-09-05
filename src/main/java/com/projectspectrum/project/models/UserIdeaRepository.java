package com.projectspectrum.project.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdeaRepository extends JpaRepository<UserIdea, Long> {
  public UserIdea findByUser(String user);
  public UserIdea findById(long id);
  public UserIdea findByTitle(String title);
  public UserIdea findByUserId(long id);
}
