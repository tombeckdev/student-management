package com.login.models.dto;

import com.login.models.enums.UserRole;

import jakarta.validation.constraints.NotNull;

public record RegisterDto(String email, String password,@NotNull UserRole userRole) {

}