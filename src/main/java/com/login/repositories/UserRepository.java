package com.login.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.login.models.entities.User;

public interface UserRepository extends JpaRepository<User, UUID>{

    UserDetails findByEmail(String email);
    
}
