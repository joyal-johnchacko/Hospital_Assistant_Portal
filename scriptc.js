<script>
  document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("appointmentForm");
    const successMessage = document.getElementById("successMessage");

    form.addEventListener("submit", function(e) {
      e.preventDefault();
      const data = new FormData(form);

      const appointment = {
        id: Date.now(),
        patientName: data.get("patientName"),
        patientAge: data.get("patientAge"),
        date: data.get("appointmentDate"),
        time: data.get("appointmentTime"),
        department: data.get("department"),
        doctorName: data.get("doctorName"),
        reason: data.get("reason"),
        status: "Waiting"
      };

      // Get existing appointments from localStorage
      let appointments = JSON.parse(localStorage.getItem("hospitalAppointments")) || [];
      appointments.push(appointment);

      // Save back to localStorage
      localStorage.setItem("hospitalAppointments", JSON.stringify(appointments));

      // Show success message
      successMessage.style.display = "block";

      // Redirect back to dashboard after 1.5s
      setTimeout(() => {
        window.location.href = "index.html";
      }, 1500);
    });
  });
</script>
