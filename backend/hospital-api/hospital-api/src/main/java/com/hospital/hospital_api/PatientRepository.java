package com.hospital.hospital_api;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("SELECT * FROM patients WHERE email = :email")
    Optional<Patient> findByEmail(String email);

}