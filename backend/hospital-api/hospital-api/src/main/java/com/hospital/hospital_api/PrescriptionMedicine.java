package com.hospital.hospital_api;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("prescription_medicines")
public class PrescriptionMedicine {

    @Id
    private Long id;

    private String name;
    private String dose;

    @Column("times_per_day")
    private int timesPerDay;

    @Column("duration_days")
    private int durationDays;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
    public int getTimesPerDay() { return timesPerDay; }
    public void setTimesPerDay(int timesPerDay) { this.timesPerDay = timesPerDay; }
    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }
}