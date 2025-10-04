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

        String generatedPassword = newDoctor.getFullName().replaceAll("\\s+", "") + "@123";
        newDoctor.setPassword(generatedPassword);

        newDoctor.setDateAdded(LocalDateTime.now());

        doctorRepository.save(newDoctor);

        return ResponseEntity.status(HttpStatus.CREATED).body("Doctor added successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginDoctor(@RequestBody Doctor loginRequest) {
        Optional<Doctor> doctorOptional = doctorRepository.findByFullName(loginRequest.getFullName());

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            if (doctor.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(doctor);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password.");
    }

    @GetMapping
    public Iterable<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok("Doctor deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found.");
        }
    }

    // THIS IS THE NEWLY ADDED METHOD
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            return ResponseEntity.ok(doctor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}