package com.riwi.template.domain.ports.services;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.requests.LoginRequest;

public interface AuthService {
    public LoginResponse login(LoginRequest request);
}
