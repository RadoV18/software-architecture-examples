package com.example.user.repository;

import com.example.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByAuthorizationCode(String authorizationCode);
}
