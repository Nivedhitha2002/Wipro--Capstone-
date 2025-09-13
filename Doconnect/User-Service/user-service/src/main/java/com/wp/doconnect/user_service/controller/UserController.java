package com.wp.doconnect.user_service.controller;


import com.wp.doconnect.user_service.entity.User;
import com.wp.doconnect.user_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // Register User
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> data) {
        var userOpt = repo.findByUsername(data.get("username"));
        if (userOpt.isEmpty()) return ResponseEntity.status(404).body("User not found");

        var user = userOpt.get();
        if (!encoder.matches(data.get("password"), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }
        return ResponseEntity.ok("Login successful ✅");
    }

    // Get all Users
    @GetMapping
    public java.util.List<User> getAll() {
        return repo.findAll();
    }
}
