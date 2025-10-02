package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupPatient(@RequestBody Patient newPatient) {
        if (patientRepository.findByEmail(newPatient.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use.");
        }
        newPatient.setCreatedAt(LocalDateTime.now());
        patientRepository.save(newPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient account created successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPatient(@RequestBody Patient loginRequest) {
        Optional<Patient> patientOptional = patientRepository.findByFullName(loginRequest.getFullName());
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            if (patient.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(patient);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password.");
    }

    // NEW METHOD to get all patients
    @GetMapping
    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // NEW METHOD to delete a patient by their ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return ResponseEntity.ok("Patient deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
        }
    }
}