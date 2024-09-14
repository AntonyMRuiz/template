package com.riwi.template.controllers;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.requests.LoginRequest;
import com.riwi.template.domain.ports.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(this.authService.login(request));
    }
}
