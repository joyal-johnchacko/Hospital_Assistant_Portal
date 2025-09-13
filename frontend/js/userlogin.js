document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");

    loginForm.addEventListener("submit", function(e) {
        e.preventDefault();

        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();
        const role = document.getElementById("role").value;

        if (role === "select") {
            alert("⚠️ Please choose a role!");
            return;
        }

        // ✅ Redirect based on role
        if (role === "patient") {
            localStorage.setItem("patientUsername", username || "Patient");
            window.location.href = "../Patient/dashboard.html";
        } 
        else if (role === "doctor") {
            localStorage.setItem("doctorUsername", username || "Doctor");
            window.location.href = "../Doctor/dashboard.html";
        } 
        else if (role === "receptionist") {
            localStorage.setItem("receptionistUsername", username || "Receptionist");
            window.location.href = "../Receptionist/index.html";
    } 
        else if (role === "admin") {
            localStorage.setItem("adminUsername", username || "Admin");
            window.location.href = "../Admin/admin.html";
        }
    });
});
