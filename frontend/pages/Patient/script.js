document.addEventListener("DOMContentLoaded", () => {
  const appointmentForm = document.getElementById("appointmentForm");
  const appointmentList = document.getElementById("appointmentList");
  const prescForm = document.getElementById("prescriptionForm");

  let appointments = JSON.parse(localStorage.getItem("appointments")) || [];

  /** ---------- Appointment Management ---------- **/
  function renderAppointments() {
    if (!appointmentList) return;

    appointmentList.innerHTML = "";
    appointments.forEach((appt, index) => {
      let row = document.createElement("tr");
      row.innerHTML = `
        <td>${appt.patientName}</td>
        <td>${appt.patientAge}</td>
        <td>${appt.appointmentTime}</td>
        <td>${appt.reason}</td>
        <td>
          <button class="view-btn" onclick="viewAppointment(${index})">View</button>
          <button class="delete-btn" onclick="deleteAppointment(${index})">Delete</button>
        </td>
      `;
      appointmentList.appendChild(row);
    });
  }

  if (appointmentForm) {
    appointmentForm.addEventListener("submit", (e) => {
      e.preventDefault();
      const newAppt = {
        patientName: appointmentForm.patientName.value,
        patientAge: appointmentForm.patientAge.value,
        appointmentTime: appointmentForm.appointmentTime.value,
        reason: appointmentForm.reason.value
      };
      appointments.push(newAppt);
      localStorage.setItem("appointments", JSON.stringify(appointments));

      // ✅ Show success message instead of redirect
      alert("Appointment booked successfully!");

      // ✅ Clear form
      appointmentForm.reset();
    });
  }

  window.deleteAppointment = function (index) {
    if (confirm("Are you sure you want to delete this appointment?")) {
      appointments.splice(index, 1);
      localStorage.setItem("appointments", JSON.stringify(appointments));
      renderAppointments();
    }
  };

  window.viewAppointment = function (index) {
    localStorage.setItem("selectedAppointment", JSON.stringify(appointments[index]));
    window.location.href = "prescription.html"; // Open prescription form
  };

  /** ---------- Prescription Form ---------- **/
  if (prescForm) {
    const selectedAppt = JSON.parse(localStorage.getItem("selectedAppointment"));
    if (selectedAppt) {
      document.getElementById("prescPatientName").value = selectedAppt.patientName;
    } else {
      alert("No appointment selected. Redirecting...");
      window.location.href = "appointments.html";
    }

    const medicinesList = document.getElementById("medicinesList");
    const addMedicineBtn = document.getElementById("addMedicineBtn");

    // Create medicine row
    function createMedicineRow() {
      const row = document.createElement("div");
      row.className = "medicine-row";
      row.innerHTML = `
        <span class="med-index"></span>
        <input type="text" placeholder="Medicine name" class="med-name" required>
        <input type="text" placeholder="Concentration (e.g. 500mg)" class="med-dose" required>
        <input type="number" placeholder="Times/day" class="med-times" min="1" required>
        <button type="button" class="remove-btn" style="color:red;">✖</button>
      `;

      // Remove row
      row.querySelector(".remove-btn").addEventListener("click", () => {
        row.remove();
        updateMedicineNumbers();
      });

      medicinesList.appendChild(row);
      updateMedicineNumbers();
    }

    function updateMedicineNumbers() {
      medicinesList.querySelectorAll(".medicine-row").forEach((row, index) => {
        row.querySelector(".med-index").innerText = (index + 1) + ".";
      });
    }

    // Add first row
    createMedicineRow();

    addMedicineBtn.addEventListener("click", createMedicineRow);

    prescForm.addEventListener("submit", function (e) {
      e.preventDefault();

      // Collect medicines
      const medicines = Array.from(medicinesList.querySelectorAll(".medicine-row")).map(row => ({
        name: row.querySelector(".med-name").value,
        dose: row.querySelector(".med-dose").value,
        times: row.querySelector(".med-times").value
      }));

      const prescription = {
        patientName: selectedAppt.patientName,
        patientAge: selectedAppt.patientAge,
        diagnosis: document.getElementById("diagnosis").value,
        medicines: medicines,
        advice: document.getElementById("advice").value,
      };

      // Attach to appointment
      const apptIndex = appointments.findIndex(
        appt =>
          appt.patientName === selectedAppt.patientName &&
          appt.appointmentDate === selectedAppt.appointmentDate &&
          appt.appointmentTime === selectedAppt.appointmentTime
      );
      if (apptIndex > -1) {
        appointments[apptIndex].prescription = prescription;
        localStorage.setItem("appointments", JSON.stringify(appointments));
      }

      localStorage.setItem("prescription", JSON.stringify(prescription));
      window.open("print.html", "_blank");
    });
  }

  /** ---------- Printable Prescription ---------- **/
  if (window.location.pathname.endsWith("print.html")) {
    const presc = JSON.parse(localStorage.getItem("prescription"));
    if (presc) {
      document.getElementById("printPatient").innerText = presc.patientName;
      document.getElementById("printAge").innerText = presc.patientAge;
      document.getElementById("printDiagnosis").innerText = presc.diagnosis;
      document.getElementById("printAdvice").innerText = presc.advice;

      // Target the <tbody> element
      const medTbody = document.getElementById("printMedicines");
      medTbody.innerHTML = ""; // Clear any previous content

      // Create and append a table row for each medicine
      presc.medicines.forEach((med, index) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${index + 1}</td>
          <td>${med.name}</td>
          <td>${med.dose}</td>
          <td>${med.times} / day</td>
        `;
        medTbody.appendChild(row);
      });
    }
  }

  /** ---------- Auto-render appointments ---------- **/
  renderAppointments();
});

/** ---------- Patient Prescription View ---------- **/
if (window.location.pathname.endsWith("prescriptions.html")) {
  const table = document.querySelector("table");
  const appointments = JSON.parse(localStorage.getItem("appointments")) || [];

  if (table) {
    const tbody = document.createElement("tbody");

    // ✅ Show all prescriptions saved by doctor
    const prescriptions = appointments.filter(appt => appt.prescription);
    if (prescriptions.length > 0) {
      prescriptions.forEach(appt => {
        appt.prescription.medicines.forEach(med => {
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${med.name}</td>
            <td>${med.dose}</td>
            <td>${med.times} / day</td>
            <td>${appt.prescription.advice || "-"}</td>
          `;
          tbody.appendChild(row);
        });
      });
    } else {
      const row = document.createElement("tr");
      row.innerHTML = `<td colspan="4">No prescriptions found.</td>`;
      tbody.appendChild(row);
    }

    table.appendChild(tbody);
  }
}
if (appointmentForm) {
  appointmentForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const newAppt = {
      patientName: appointmentForm.patientName.value,
      patientAge: appointmentForm.patientAge.value,
      appointmentTime: appointmentForm.appointmentTime.value,
      reason: appointmentForm.reason.value
    };
    appointments.push(newAppt);
    localStorage.setItem("appointments", JSON.stringify(appointments));

    // ❌ Old alert removed
    // alert("Appointment booked successfully!");

    // ✅ New success message
    const successMessage = document.getElementById("successMessage");
    successMessage.style.display = "block";

    setTimeout(() => {
      successMessage.style.display = "none";
    }, 3000); // hide after 3 seconds

    // ✅ Clear form
    appointmentForm.reset();
  });
}

