package com.example.bankrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthManagerConfig {
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService uds, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider prov = new DaoAuthenticationProvider();
        prov.setUserDetailsService(uds);
        prov.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(prov);
    }
}
