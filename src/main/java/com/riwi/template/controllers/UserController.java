package com.riwi.template.controllers;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.reponses.UserResponse;
import com.riwi.template.application.dtos.requests.UserRegisterRequest;
import com.riwi.template.domain.enums.Role;
import com.riwi.template.domain.ports.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<LoginResponse> registerAdmin(
            @Validated @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(this.userService.register(request, Role.ADMIN));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> registerCustomer(
            @Validated @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(this.userService.register(request, Role.CUSTOMER));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
