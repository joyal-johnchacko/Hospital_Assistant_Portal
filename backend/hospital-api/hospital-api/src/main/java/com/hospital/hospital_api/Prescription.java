package com.hospital.hospital_api;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Table("prescriptions")
public class Prescription {

    @Id
    private Long id;

    @Column("appointment_id")
    private Long appointmentId;

    @Column("patient_id")
    private Long patientId;

    @Column("doctor_id")
    private Long doctorId;

    private String diagnosis;
    private String advice;

    @Column("prescription_date")
    private LocalDateTime prescriptionDate;

    @MappedCollection(idColumn = "prescription_id")
    private Set<PrescriptionMedicine> medicines;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getAdvice() { return advice; }
    public void setAdvice(String advice) { this.advice = advice; }
    public LocalDateTime getPrescriptionDate() { return prescriptionDate; }
    public void setPrescriptionDate(LocalDateTime prescriptionDate) { this.prescriptionDate = prescriptionDate; }
    public Set<PrescriptionMedicine> getMedicines() { return medicines; }
    public void setMedicines(Set<PrescriptionMedicine> medicines) { this.medicines = medicines; }
}