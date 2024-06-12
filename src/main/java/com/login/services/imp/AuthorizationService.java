package com.login.services.imp;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.login.models.dto.AuthenticationDto;
import com.login.models.dto.LoginResponseDto;
import com.login.models.dto.RegisterDto;
import com.login.models.entities.User;
import com.login.repositories.UserRepository;
import com.login.security.TokenService;

import jakarta.validation.Valid;

@Validated
@Service
public class AuthorizationService implements UserDetailsService {

    private final ApplicationContext context;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationService(ApplicationContext context, UserRepository userRepository, TokenService tokenService) {
        this.context = context;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        if (this.userRepository.findByEmail(registerDto.email()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        User newUser = new User(registerDto.email(), encryptedPassword, registerDto.userRole());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}