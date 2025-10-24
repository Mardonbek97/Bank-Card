package com.example.bankrest.service;

import com.example.bankrest.dto.RegisterDto;
import com.example.bankrest.dto.UserStatusChangeDto;
import com.example.bankrest.entity.User;
import com.example.bankrest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) throw new RuntimeException("Имя пользователя существует");
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRole("ROLE_USER");
        userRepository.save(u);
    }

    public void createAdminIfNotExists() {
        userRepository.findByUsername("user").orElseGet(() -> {
            User u = new User();
            u.setUsername("admin");
            u.setPassword(passwordEncoder.encode("admin123"));
            u.setRole("ROLE_ADMIN");
            return userRepository.save(u);
        });
    }

    public List<UserStatusChangeDto> changeUserStatus(UUID userId, String active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setStatus(active);
        userRepository.save(user);

        return userRepository.findAll()
                .stream()
                .map(u -> new UserStatusChangeDto(u.getId(),
                        u.getUsername(),
                        u.getStatus()))
                .collect(Collectors.toList());

    }

    public List<UserStatusChangeDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserStatusChangeDto(u.getId(),
                        u.getUsername(),
                        u.getStatus()))
                .collect(Collectors.toList());

    }
}
