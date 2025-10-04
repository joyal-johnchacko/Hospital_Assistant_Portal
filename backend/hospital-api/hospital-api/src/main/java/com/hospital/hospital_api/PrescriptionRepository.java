package com.hospital.hospital_api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    // This method will find all prescriptions for a given patient's ID
    List<Prescription> findByPatientId(Long patientId);
}