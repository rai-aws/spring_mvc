package com.spring.spring_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.spring_security.entity.Auth_Role;

/**
 * Repository interface for database operations on Auth_Role entities.
 */
public interface AuthRoleRepository extends JpaRepository<Auth_Role, Long>{
    Optional<Auth_Role> findByName(String theName);
    Boolean existsByName(String theUsername);
}
