package com.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.models.dto.AuthenticationDto;
import com.login.models.dto.RegisterDto;
import com.login.services.imp.AuthorizationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authDto) {
        return authorizationService.login(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }

}
