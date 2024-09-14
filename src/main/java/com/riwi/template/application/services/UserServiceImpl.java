package com.riwi.template.application.services;

import com.riwi.template.application.dtos.reponses.LoginResponse;
import com.riwi.template.application.dtos.reponses.UserResponse;
import com.riwi.template.application.dtos.requests.UserRegisterRequest;
import com.riwi.template.domain.entities.User;
import com.riwi.template.domain.enums.Role;
import com.riwi.template.domain.ports.services.UserService;
import com.riwi.template.infrastructure.persistence.UserRepository;
import com.riwi.template.infrastructure.helpers.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse register(UserRegisterRequest request, Role role) {

        User userDB = userRepository.findByUsernameOrEmail(request.getUsername(),request.getEmail());

        if (userDB != null){
            throw new RuntimeException("Username register");
        }

        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        user = this.userRepository.save(user);

        return UserResponse.builder()
                .message(user.getRole() + " successfully authenticated")
                .token(this.jwtUtil.generateToken(user))
                .id(user.getId())
                .name(user.getName())
                .build();

    }
}
