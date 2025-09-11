const signupForm = document.getElementById("signupForm");

signupForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const newPatient = {
        fullName: document.getElementById("fullName").value,
        password: document.getElementById("password").value,
        age: document.getElementById("age").value,
        phone: document.getElementById("phone").value
    };

    // Get existing patients or an empty array if none exist
    let patients = JSON.parse(localStorage.getItem("patients")) || [];

    // Add new patient to the array
    patients.push(newPatient);

    // Save the updated array back to localStorage
    localStorage.setItem("patients", JSON.stringify(patients));

    alert("Signup successful!");
    signupForm.reset();
});