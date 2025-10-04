package com.hospital.hospital_api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    // This will be useful later for the doctor's dashboard
    List<Appointment> findByDoctorId(Long doctorId);
}