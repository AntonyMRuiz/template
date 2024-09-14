package com.riwi.template.infrastructure.security;

import com.riwi.template.domain.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private final String[] PUBLIC_ENDPOINTS = { "/auth/login" , "/user/register", "/swagger-ui/**", "/v3/api-docs/**" };

    private final String[] ADMIN_ENDPOINTS = { "/user/admin/register" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(ADMIN_ENDPOINTS).hasAuthority(Role.CUSTOMER.name())
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}
