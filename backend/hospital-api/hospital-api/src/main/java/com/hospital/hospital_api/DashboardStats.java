package com.hospital.hospital_api;

// This is a simple class to hold our dashboard data.
// It's not a database table, just a container.
public class DashboardStats {

    private long totalUsers;

    // Getters and Setters
    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }
}