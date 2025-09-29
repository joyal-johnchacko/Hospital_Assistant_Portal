package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupPatient(@RequestBody Patient newPatient) {
        Optional<Patient> existingPatient = patientRepository.findByEmail(newPatient.getEmail());

        if (existingPatient.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use.");
        }

        newPatient.setCreatedAt(LocalDateTime.now());
        patientRepository.save(newPatient);

        return ResponseEntity.status(HttpStatus.CREATED).body("Patient account created successfully.");
    }
}