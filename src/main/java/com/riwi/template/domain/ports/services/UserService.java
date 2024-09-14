package com.riwi.template.domain.ports.services;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.requests.UserRegisterRequest;
import com.riwi.template.domain.enums.Role;

public interface UserService {
    public LoginResponse register(UserRegisterRequest request, Role role);
}
