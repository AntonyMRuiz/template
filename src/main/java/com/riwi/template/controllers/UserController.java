package com.riwi.template.controllers;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.reponses.UserResponse;
import com.riwi.template.application.dtos.requests.UserRegisterRequest;
import com.riwi.template.domain.enums.Role;
import com.riwi.template.domain.ports.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@Tag(name = "User", description = "Manages user-related requests.")
public class UserController {
    @Autowired
    private final UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("admin/register")
    @Operation(
            summary = "Create an admin.",
            description = "Provides the user data to create it and the token to validate the permissions.")
    public ResponseEntity<LoginResponse> registerAdmin(
            @Validated @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(this.userService.register(request, Role.ADMIN));
    }

    @PostMapping("register")
    @Operation(
            summary = "Create a customer.",
            description = "Provides the user's data to create the client.")
    public ResponseEntity<LoginResponse> registerCustomer(
            @Validated @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(this.userService.register(request, Role.CUSTOMER));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(
            summary = "List all users.",
            description = "Provide the token to validate the permissions and obtain the list of users.")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
