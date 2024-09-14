package com.riwi.template.application.services;

import com.riwi.template.domain.entities.User;
import com.riwi.template.infrastructure.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String identifier) {

        User user = userRepository.findByUsernameOrEmail(identifier, identifier);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
