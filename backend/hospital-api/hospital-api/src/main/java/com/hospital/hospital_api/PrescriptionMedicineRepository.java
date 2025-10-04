package com.hospital.hospital_api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionMedicineRepository extends CrudRepository<PrescriptionMedicine, Long> {
    // We don't need any custom methods here for now
}