package com.hospital.hospital_api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final JdbcTemplate jdbcTemplate;

    // We no longer need all the repositories, just a direct way to query the database
    public DashboardController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/stats")
    public DashboardStats getDashboardStats() {
        // This is a single, efficient SQL query that gets all counts at once
        String sql = "SELECT 'patients' as user_type, COUNT(*) as count FROM patients UNION ALL " +
                "SELECT 'doctors' as user_type, COUNT(*) as count FROM doctors UNION ALL " +
                "SELECT 'receptionists' as user_type, COUNT(*) as count FROM receptionists UNION ALL " +
                "SELECT 'admins' as user_type, COUNT(*) as count FROM admins";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        long totalUsers = 0;
        for (Map<String, Object> row : results) {
            totalUsers += (Long) row.get("count");
        }

        DashboardStats stats = new DashboardStats();
        stats.setTotalUsers(totalUsers);

        return stats;
    }
}