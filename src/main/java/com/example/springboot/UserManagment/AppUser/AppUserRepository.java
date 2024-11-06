package com.example.springboot.UserManagment.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<Object> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
