package com.example.bankrest.config;

import com.example.bankrest.entity.User;
import com.example.bankrest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.findByUsername("admin").orElseGet(() -> {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(passwordEncoder.encode("admin123"));
                u.setRole("ROLE_ADMIN");
                return userRepository.save(u);
            });
        };
    }
}
