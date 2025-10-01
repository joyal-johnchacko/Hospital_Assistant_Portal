package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/receptionists")
public class ReceptionistController {

    private final ReceptionistRepository receptionistRepository;

    public ReceptionistController(ReceptionistRepository receptionistRepository) {
        this.receptionistRepository = receptionistRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReceptionist(@RequestBody Receptionist newReceptionist) {
        if (receptionistRepository.findByEmail(newReceptionist.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use.");
        }
        String generatedPassword = newReceptionist.getFullName() + "@123";
        newReceptionist.setPassword(generatedPassword);
        newReceptionist.setDateAdded(LocalDateTime.now());
        receptionistRepository.save(newReceptionist);
        return ResponseEntity.status(HttpStatus.CREATED).body("Receptionist added successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginReceptionist(@RequestBody Receptionist loginRequest) {
        Optional<Receptionist> receptionistOptional = receptionistRepository.findByFullName(loginRequest.getFullName());
        if (receptionistOptional.isPresent()) {
            Receptionist receptionist = receptionistOptional.get();
            if (receptionist.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(receptionist);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid full name or password.");
    }

    // New method to get all receptionists
    @GetMapping
    public Iterable<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }

    // New method to delete a receptionist by their ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReceptionist(@PathVariable Long id) {
        if (receptionistRepository.existsById(id)) {
            receptionistRepository.deleteById(id);
            return ResponseEntity.ok("Receptionist deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receptionist not found.");
        }
    }
}

