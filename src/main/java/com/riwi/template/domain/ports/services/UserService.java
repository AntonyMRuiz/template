package com.riwi.template.domain.ports.services;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.reponses.UserResponse;
import com.riwi.template.application.dtos.requests.UserRegisterRequest;
import com.riwi.template.domain.enums.Role;

import java.util.List;

public interface UserService {
    public LoginResponse register(UserRegisterRequest request, Role role);
    public List<UserResponse> getAllUsers();
}
