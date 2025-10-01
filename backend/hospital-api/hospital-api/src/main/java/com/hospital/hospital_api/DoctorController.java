package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor newDoctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findByEmail(newDoctor.getEmail());
        if (existingDoctor.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use.");
        }

        String generatedPassword = newDoctor.getFullName() + "@123";
        newDoctor.setPassword(generatedPassword);

        newDoctor.setDateAdded(LocalDateTime.now());

        doctorRepository.save(newDoctor);

        return ResponseEntity.status(HttpStatus.CREATED).body("Doctor added successfully.");
    }

    // Add this new method for doctor login
    @PostMapping("/login")
    public ResponseEntity<?> loginDoctor(@RequestBody Doctor loginRequest) {
        // 1. Find the doctor by their full name
        Optional<Doctor> doctorOptional = doctorRepository.findByFullName(loginRequest.getFullName());

        // 2. Check if the doctor exists and if the password matches
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            if (doctor.getPassword().equals(loginRequest.getPassword())) {
                // 3. If credentials are correct, return a success response with the doctor's data
                return ResponseEntity.ok(doctor);
            }
        }

        // 4. If doctor not found or password incorrect, return an error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password.");
    }
}