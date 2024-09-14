package com.riwi.template.application.dtos.reponses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class LoginResponse {
    private String message;
    private String token;
}
