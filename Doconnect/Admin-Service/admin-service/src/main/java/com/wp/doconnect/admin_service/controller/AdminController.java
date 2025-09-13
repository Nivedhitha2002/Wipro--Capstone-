package com.wp.doconnect.admin_service.controller;

import com.wp.doconnect.admin_service.entity.Admin;
import com.wp.doconnect.admin_service.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AdminController(AdminRepository repo) {
        this.repo = repo;
    }

    // Register Admin
    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword())); // encrypt password
        return repo.save(admin);
    }

    //  Login Admin
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> data) {
        Optional<Admin> adminOpt = repo.findByUsername(data.get("username"));

        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Admin not found ❌");
        }

        Admin admin = adminOpt.get();

        if (!encoder.matches(data.get("password"), admin.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password ❌");
        }

        return ResponseEntity.ok("Admin login successful ✅");
    }

    // Get all Admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return repo.findAll();
    }
}
