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

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        prescription.setPrescriptionDate(LocalDateTime.now());
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrescription);
    }

    @GetMapping("/patient/{patientId}")
    public List<Prescription> getPrescriptionsByPatientId(@PathVariable Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    // Add this new method
    @GetMapping("/doctor/{doctorId}")
    public List<Prescription> getPrescriptionsByDoctorId(@PathVariable Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }
}