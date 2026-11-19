package com.spring.spring_security.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for role registration.
 * Holds the name of the role to be registered.
 */
public class AuthRoleRegisterDto {
    @NotBlank 
    private String name;

    public AuthRoleRegisterDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }  
}
