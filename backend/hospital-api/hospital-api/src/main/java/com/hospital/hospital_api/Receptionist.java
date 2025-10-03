package com.hospital.hospital_api;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate; // Correct import

@Table("receptionists")
public class Receptionist {
    @Id
    private Long id;
    @Column("full_name")
    private String fullName;
    private String email;
    private String phone;
    private int age;
    private String gender;
    private String password;
    @Column("date_added")
    private LocalDate dateAdded; // Correct type

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public LocalDate getDateAdded() { return dateAdded; } // Correct type
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; } // Correct type

    @Override
    public String toString() {
        return "Receptionist{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}