package com.spring.spring_security.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for user login.
 * Holds the username and password provided during authentication.
 */
public class AuthLoginDto {
    
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public AuthLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
