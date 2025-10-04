package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    // Endpoint for a doctor to SAVE a new prescription
    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        prescription.setPrescriptionDate(LocalDateTime.now());

        // Spring Data JDBC saves the main prescription and all medicine objects inside it
        Prescription savedPrescription = prescriptionRepository.save(prescription);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrescription);
    }

    // Endpoint for a patient to GET all their prescriptions
    @GetMapping("/patient/{patientId}")
    public List<Prescription> getPrescriptionsByPatientId(@PathVariable Long patientId) {
        // This finds all prescriptions for the patient and automatically includes the medicines for each one
        return prescriptionRepository.findByPatientId(patientId);
    }
}