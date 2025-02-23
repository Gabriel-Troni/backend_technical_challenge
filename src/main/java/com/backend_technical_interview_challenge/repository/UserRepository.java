package com.backend_technical_interview_challenge.repository;

import com.backend_technical_interview_challenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByDocument(String document);

public Optional<User> findUserById(Long id);
}
