package com.projectspectrum.project.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
  public ApplicationUser findByEmail(String email);
  public ApplicationUser findById(long id);
}
