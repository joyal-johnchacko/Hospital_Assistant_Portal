package com.hospital.hospital_api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    List<Prescription> findByPatientId(Long patientId);

    // Add this new method
    List<Prescription> findByDoctorId(Long doctorId);
}