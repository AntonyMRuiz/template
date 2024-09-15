package com.riwi.template.application.services;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.requests.LoginRequest;
import com.riwi.template.domain.entities.User;
import com.riwi.template.domain.exceptions.InvalidCredentialsException;
import com.riwi.template.infrastructure.helpers.JwtUtil;
import com.riwi.template.domain.ports.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = (User) userDetailsService.loadUserByUsername(request.getUsernameOrEmail());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return LoginResponse.builder()
                .message(user.getRole() + " successfully authenticated")
                .token(this.jwtUtil.generateToken(user))
                .build();
    }
}
