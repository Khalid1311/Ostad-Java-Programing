package com.example.OstadAssignment15.service;

import com.example.OstadAssignment15.entity.User;
import com.example.OstadAssignment15.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User userRegister(User user) {
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public User adminRegister(User user) {
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user);
    }
}
