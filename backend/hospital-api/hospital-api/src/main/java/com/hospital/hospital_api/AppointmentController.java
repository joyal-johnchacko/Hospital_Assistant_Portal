package com.hospital.hospital_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment newAppointment) {
        newAppointment.setCreatedAt(LocalDateTime.now());
        Appointment savedAppointment = appointmentRepository.save(newAppointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }

    // NEW METHOD: Get all appointments for a specific doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // NEW METHOD: Delete a specific appointment by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}