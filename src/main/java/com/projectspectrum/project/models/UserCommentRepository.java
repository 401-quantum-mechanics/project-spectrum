package com.projectspectrum.project.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepository extends JpaRepository<UserComment, Long> {
    public UserComment findById(long id);
    public void delete(UserComment userComment);
}
