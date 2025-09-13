
<script>
document.addEventListener("DOMContentLoaded", () => {
    let appointments = [];
    let currentFilterDate = null; // null means show today's appointments

    // Load data from memory (using a simple array since localStorage is restricted)
    function loadData() {
        const storedAppointments = getStoredAppointments();
        appointments = storedAppointments ? storedAppointments : [];
        console.log("Loaded appointments:", appointments);
    }

    // Simple storage simulation (since localStorage is not supported in Claude artifacts)
    let memoryStorage = [];
    
    function getStoredAppointments() {
        // In a real application, this would use localStorage
        // For demo purposes, we'll use a memory array
        return memoryStorage;
    }

    function saveAppointments(data) {
        // In a real application, this would save to localStorage
        memoryStorage = data;
    }

    // Initialize date filter with today's date
    function initializeDateFilter() {
        const today = new Date().toISOString().split("T")[0];
        const dateFilter = document.getElementById("dateFilter");
        if (dateFilter) {
            dateFilter.value = today;
            currentFilterDate = today;
        }
        updateCurrentDateDisplay();
    }

    // Update current date display
    function updateCurrentDateDisplay() {
        const display = document.getElementById("currentDateDisplay");
        if (display) {
            if (currentFilterDate) {
                const date = new Date(currentFilterDate + 'T00:00:00');
                display.textContent = `Showing: ${date.toLocaleDateString('en-US', { 
                    weekday: 'long', 
                    year: 'numeric', 
                    month: 'long', 
                    day: 'numeric' 
                })}`;
            } else {
                display.textContent = "Showing: All appointments";
            }
        }
    }

    // Show all appointments
    window.showAllAppointments = function() {
        currentFilterDate = null;
        const dateFilter = document.getElementById("dateFilter");
        if (dateFilter) dateFilter.value = "";
        updateCurrentDateDisplay();
        renderAppointments();
        updateStats();
    };

    // Date filter change handler
    document.getElementById("dateFilter")?.addEventListener("change", (e) => {
        currentFilterDate = e.target.value || null;
        updateCurrentDateDisplay();
        renderAppointments();
        updateStats();
    });

    // Tab functionality
    const tabButtons = document.querySelectorAll(".tab-btn");
    const tabContents = document.querySelectorAll(".tab-content");

    tabButtons.forEach((btn) => {
        btn.addEventListener("click", () => {
            tabButtons.forEach((b) => b.classList.remove("tab-active"));
            tabContents.forEach((c) => c.classList.add("hidden"));
            
            btn.classList.add("tab-active");
            const target = document.getElementById(btn.dataset.tab + "-tab");
            if (target) target.classList.remove("hidden");
        });
    });

    // Get status color classes
    function getStatusColor(status) {
        switch (status) {
            case "Waiting":
                return "bg-yellow-100 text-yellow-800";
            case "Completed":
                return "bg-green-100 text-green-800";
            case "Cancelled":
                return "bg-red-100 text-red-800";
            default:
                return "bg-gray-100 text-gray-800";
        }
    }

    // Format time for display
    function formatTime(time) {
        const [hour, minute] = time.split(':');
        const hourInt = parseInt(hour);
        const ampm = hourInt >= 12 ? 'PM' : 'AM';
        const displayHour = hourInt % 12 || 12;
        return `${displayHour}:${minute} ${ampm}`;
    }

    // Format date for display
    function formatDate(dateStr) {
        const date = new Date(dateStr + 'T00:00:00');
        return date.toLocaleDateString('en-US', { 
            month: 'short', 
            day: 'numeric',
            year: 'numeric'
        });
    }

    // Get filtered appointments
    function getFilteredAppointments() {
        if (currentFilterDate) {
            return appointments.filter(appt => appt.date === currentFilterDate);
        } else {
            return appointments;
        }
    }

    // Render appointments table
    function renderAppointments() {
        const tbody = document.getElementById("appointmentsTable");
        if (!tbody) return;

        tbody.innerHTML = "";
        
        const filteredAppointments = getFilteredAppointments();

        console.log("Current filter date:", currentFilterDate);
        console.log("Filtered appointments:", filteredAppointments);

        if (filteredAppointments.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="9" class="text-center py-8 text-gray-500">
                        <div class="flex flex-col items-center">
                            <svg class="w-12 h-12 text-gray-300 mb-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3a2 2 0 012-2h4a2 2 0 012 2v4m-6 4v10a1 1 0 001 1h4a1 1 0 001-1V11M9 7h6"></path>
                            </svg>
                            <p>${currentFilterDate ? 'No appointments for selected date' : 'No appointments found'}</p>
                            <p class="text-sm">Click "New Appointment" to add one</p>
                        </div>
                    </td>
                </tr>
            `;
            return;
        }

        // Sort appointments by date and time
        filteredAppointments.sort((a, b) => {
            const dateCompare = a.date.localeCompare(b.date);
            if (dateCompare !== 0) return dateCompare;
            return a.time.localeCompare(b.time);
        });

        filteredAppointments.forEach((appt) => {
            const row = document.createElement("tr");
            row.className = "hover:bg-gray-50";
            row.innerHTML = `
                <td class="px-6 py-4 text-sm text-gray-900">${formatDate(appt.date)}</td>
                <td class="px-6 py-4 text-sm font-medium text-gray-900">${formatTime(appt.time)}</td>
                <td class="px-6 py-4 text-sm text-gray-900">${appt.patientName}</td>
                <td class="px-6 py-4 text-sm text-gray-900">${appt.patientAge}</td>
                <td class="px-6 py-4 text-sm text-gray-900">${appt.doctorName}</td>
                <td class="px-6 py-4 text-sm text-gray-900">${appt.department}</td>
                <td class="px-6 py-4 text-sm text-gray-900">${appt.reason}</td>
                <td class="px-6 py-4">
                    <span class="inline-flex px-2 py-1 text-xs font-semibold rounded-full ${getStatusColor(appt.status)}">
                        ${appt.status}
                    </span>
                </td>
                <td class="px-6 py-4 text-sm space-x-2">
                    ${appt.status === 'Waiting' ? `
                        <button onclick="updateStatus(${appt.id}, 'Completed')" class="text-green-600 hover:text-green-800 font-medium">Complete</button>
                        <button onclick="updateStatus(${appt.id}, 'Cancelled')" class="text-red-600 hover:text-red-800 font-medium">Cancel</button>
                    ` : `
                        <span class="text-gray-400">No actions</span>
                    `}
                </td>
            `;
            tbody.appendChild(row);
        });
    }

    // Update appointment status
    window.updateStatus = function(id, status) {
        const appointmentIndex = appointments.findIndex(appt => appt.id === id);
        if (appointmentIndex !== -1) {
            appointments[appointmentIndex].status = status;
            saveAppointments(appointments);
            renderAppointments();
            updateStats();
            console.log(`Updated appointment ${id} to ${status}`);
            showNotification(`Appointment ${status.toLowerCase()} successfully`, 'success');
        }
    };

    // Delete appointment function
    window.deleteAppointment = function(id) {
        if (confirm('Are you sure you want to delete this appointment? This action cannot be undone.')) {
            const appointmentIndex = appointments.findIndex(appt => appt.id === id);
            if (appointmentIndex !== -1) {
                const deletedAppointment = appointments[appointmentIndex];
                appointments.splice(appointmentIndex, 1);
                saveAppointments(appointments);
                renderAppointments();
                updateStats();
                console.log(`Deleted appointment for ${deletedAppointment.patientName}`);
                
                // Show success message
                showNotification('Appointment deleted successfully', 'success');
            }
        }
    };

    // Show notification function
    function showNotification(message, type = 'info') {
        const notification = document.createElement('div');
        notification.className = `fixed top-4 right-4 px-4 py-3 rounded-lg shadow-lg text-white z-50 transition-all duration-300 ${
            type === 'success' ? 'bg-green-500' : 
            type === 'error' ? 'bg-red-500' : 'bg-blue-500'
        }`;
        notification.textContent = message;
        document.body.appendChild(notification);
        
        // Remove after 3 seconds
        setTimeout(() => {
            notification.style.opacity = '0';
            notification.style.transform = 'translateY(-20px)';
            setTimeout(() => {
                if (notification.parentNode) {
                    notification.parentNode.removeChild(notification);
                }
            }, 300);
        }, 3000);
    }

    // Update dashboard statistics
    function updateStats() {
        const today = new Date().toISOString().split("T")[0];
        const todaysAppointments = appointments.filter(appt => appt.date === today);

        const waiting = todaysAppointments.filter(appt => appt.status === "Waiting").length;
        const completed = todaysAppointments.filter(appt => appt.status === "Completed").length;
        const total = todaysAppointments.length;
        const uniquePatients = new Set(todaysAppointments.map(appt => appt.patientName)).size;

        // Update stat cards
        const patientCountEl = document.getElementById("patientCount");
        const waitingCountEl = document.getElementById("waitingCount");
        const completedCountEl = document.getElementById("completedCount");
        const totalCountEl = document.getElementById("totalCount");

        if (patientCountEl) patientCountEl.textContent = uniquePatients;
        if (waitingCountEl) waitingCountEl.textContent = waiting;
        if (completedCountEl) completedCountEl.textContent = completed;
        if (totalCountEl) totalCountEl.textContent = total;
    }

    // Function to add sample appointments for testing
    window.addSampleAppointments = function() {
        const today = new Date().toISOString().split("T")[0];
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        const tomorrowStr = tomorrow.toISOString().split("T")[0];

        const sampleAppointments = [
            {
                id: Date.now() + 1,
                date: today,
                time: "09:00",
                patientName: "John Smith",
                patientAge: 35,
                doctorName: "Dr. Wilson",
                department: "Cardiology",
                reason: "Chest pain",
                status: "Waiting"
            },
            {
                id: Date.now() + 2,
                date: today,
                time: "14:30",
                patientName: "Sarah Johnson",
                patientAge: 28,
                doctorName: "Dr. Brown",
                department: "Dermatology",
                reason: "Skin consultation",
                status: "Completed"
            },
            {
                id: Date.now() + 3,
                date: tomorrowStr,
                time: "10:15",
                patientName: "Mike Davis",
                patientAge: 42,
                doctorName: "Dr. Garcia",
                department: "Orthopedics",
                reason: "Back pain",
                status: "Waiting"
            }
        ];

        appointments = [...appointments, ...sampleAppointments];
        saveAppointments(appointments);
        renderAppointments();
        updateStats();
        showNotification('Sample appointments added successfully!', 'success');
    };

    // Initial setup
    initializeDateFilter();
    loadData();
    renderAppointments();
    updateStats();

    // Add sample appointments for testing (remove this in production)
    // Uncomment the line below to add sample data
    // addSampleAppointments();
});
</script>