package com.example.OstadAssignment15.controller;

import com.example.OstadAssignment15.entity.User;
import com.example.OstadAssignment15.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<User> userRegister(@RequestBody User user){
        return ResponseEntity.ok(authService.userRegister(user));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> adminRegister(@RequestBody User user){
        return ResponseEntity.ok(authService.adminRegister(user));
    }
}
