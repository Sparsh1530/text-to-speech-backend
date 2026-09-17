package com.example.backend.controller;

import com.example.backend.dto.AuthRequest;
import com.example.backend.dto.AuthResponse;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(
                new AuthResponse(false, "Email is already registered!", null, null)
            );
        }

        User newUser = new User(request.getName(), request.getEmail(), request.getPassword());
        userRepository.save(newUser);

        return ResponseEntity.ok(
            new AuthResponse(true, "Registration successful!", newUser.getName(), newUser.getEmail())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(request.getPassword())) {
            User user = userOpt.get();
            return ResponseEntity.ok(
                new AuthResponse(true, "Login successful!", user.getName(), user.getEmail())
            );
        }

        return ResponseEntity.badRequest().body(
            new AuthResponse(false, "Invalid email or password!", null, null)
        );
    }
}