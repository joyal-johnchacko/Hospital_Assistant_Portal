package com.hospital.hospital_api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReceptionistRepository extends CrudRepository<Receptionist, Long> {
    Optional<Receptionist> findByEmail(String email);
    Optional<Receptionist> findByFullName(String fullName);
}