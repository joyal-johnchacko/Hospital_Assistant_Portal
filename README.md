# 🏥 Hospital Assistant Portal (HAP)

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A professional, full-stack Hospital Management System designed to digitize medical workflows. This portal implements a secure **4-Role RBAC (Role-Based Access Control)** system, allowing seamless interaction between Admins, Doctors, Receptionists, and Patients.

---

## 🌟 Key Features

### 🔐 Advanced Authentication
- **RBAC System:** Separate dashboards and permissions for **Admin, Doctor, Receptionist, and Patient**.
- **Secure Login:** Integrated validation for username/password with role-specific redirection.

### 👨‍⚕️ Medical Workflow
- **Appointment Lifecycle:** Real-time scheduling, status updates (Pending → Confirmed → Completed).
- **Digital Prescriptions:** Doctors can issue structured medical records including diagnosis, dosage, and frequency.
- **Receptionist Dashboard:** Centralized hub to manage doctor availability and patient check-ins.
- **Patient History:** Secure access for patients to view their previous consultations and prescriptions.

---

## 🏗 Project Architecture

The project follows a modern **Decoupled Architecture** (Frontend & Backend separation):

### Backend (Java + MySQL)
- **MVC Pattern:** Model-View-Controller architecture for clean code separation.
- **Data Persistence:** Spring Data JPA with Hibernate for efficient MySQL interaction.
- **RESTful API:** Exposes endpoints for CRUD operations on Users, Appointments, and Prescriptions.

### Frontend (Modern Web)
- **Responsive UI:** Built with HTML5 and CSS3 for cross-device compatibility.
- **Dynamic Logic:** JavaScript (Vanilla/ES6) for asynchronous API calls and DOM manipulation.

---

## 📂 Directory Structure

```text
Hospital_Assistant_Portal/
├── backend/
│   └── hospital-api/          
│       ├── src/main/java/     # Controllers, Models, Repositories, Services
│       └── src/main/resources/# application.properties & Static Config
├── frontend/
│   ├── css/                   # Professional Stylesheets
│   ├── js/                    # API Integration & Logic
│   └── pages/                 # Role-specific HTML Dashboards
│       ├── Admin/             # Staff management pages
│       ├── Doctor/            # Patient queue & prescription tools
│       ├── Receptionist/      # Scheduling & Registration
│       ├── Patient/           # Booking & Medical History
│       └── Login/             # Authentication Portal
└── database/
    └── hospital_db.sql        # Production-ready MySQL Schema & Seed Data
