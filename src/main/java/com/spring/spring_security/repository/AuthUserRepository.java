package com.spring.spring_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.spring_security.entity.Auth_User;

/**
 * Repository interface for database operations on Auth_User entities.
 */
public interface AuthUserRepository extends JpaRepository<Auth_User, Long>{
    Optional<Auth_User> findByUsername(String theUsername);
    Boolean existsByUsername(String theUsername);
}
