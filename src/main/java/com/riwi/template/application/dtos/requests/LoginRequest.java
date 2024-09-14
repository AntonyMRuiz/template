package com.riwi.template.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
    @NotBlank(message = "username or email required")
    private String usernameOrEmail;

    @NotBlank(message = "password required")
    private String password;
}
