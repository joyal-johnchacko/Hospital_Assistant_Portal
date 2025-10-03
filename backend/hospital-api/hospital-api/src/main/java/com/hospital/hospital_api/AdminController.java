package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin loginRequest) {
        // Find the admin by their full name
        Optional<Admin> adminOptional = adminRepository.findByFullName(loginRequest.getFullName());

        // Check if the admin exists and if the password matches
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (admin.getPassword().equals(loginRequest.getPassword())) {
                // If credentials are correct, return a success response with admin data
                return ResponseEntity.ok(admin);
            }
        }

        // If admin not found or password incorrect, return an error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password.");
    }
}