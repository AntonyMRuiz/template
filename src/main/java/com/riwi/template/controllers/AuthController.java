package com.riwi.template.controllers;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.requests.LoginRequest;
import com.riwi.template.domain.ports.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Authentication", description = "Manages authentication requests.")
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("login")
    @Operation(
            summary = "Authenticate a user.",
            description = "Provides a user's data to generate a token that allows them to use private endpoints if their role allows it.")
    public ResponseEntity<LoginResponse> login(
            @Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(this.authService.login(request));
    }
}
