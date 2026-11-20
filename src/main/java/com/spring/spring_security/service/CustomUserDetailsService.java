package com.spring.spring_security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.spring_security.entity.Auth_Role;
import com.spring.spring_security.entity.Auth_User;
import com.spring.spring_security.repository.AuthUserRepository;

/**
 * Custom implementation of UserDetailsService for loading authenticated
 * users and their assigned roles from the database.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepo;

    /**
     * @param authUserRepo repository for accessing user records
     */
    public CustomUserDetailsService(AuthUserRepository authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    /**
     * Loads a user by username for Spring Security authentication.
     *
     * @param theUsername username of the user to retrieve
     * @return user details containing the username, password, and authorities
     * @throws UsernameNotFoundException if the username does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String theUsername) {
        Auth_User user = authUserRepo.findByUsername(theUsername)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Username " + theUsername + " not found"));

        return new User(
                user.getUsername(),
                user.getPassword(),
                mapToRolesToAuthorities(user.getRoles()));
    }

    /**
     * Converts the user's assigned roles into Spring Security authorities.
     *
     * @param roles list of roles assigned to the user
     * @return collection of granted authorities
     */
    private Collection<GrantedAuthority> mapToRolesToAuthorities(
            List<Auth_Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}