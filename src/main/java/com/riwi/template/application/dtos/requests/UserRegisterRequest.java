package com.riwi.template.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRegisterRequest {
    @NotBlank(message = "name required")
    private String name;

    @NotBlank(message = "username required")
    private String username;

    @NotBlank(message = "email required")
    @Email
    private String email;

    @NotBlank(message = "password required")
    private String password;
}
