package model;

import java.sql.*;
import java.time.LocalDateTime;

public class Booking {
    private String customerName;
    private Table table;
    private LocalDateTime bookingTime;

    public Booking(String customerName, Table table, LocalDateTime bookingTime) {
        this.customerName = customerName;
        this.table = table;
        this.bookingTime = bookingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Table getTable() {
        return table;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    
    public boolean save() {
        String sql = "INSERT INTO bookings (customer_name, table_id, booking_time) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customerName);
            pstmt.setInt(2, table.getId());
            pstmt.setTimestamp(3, Timestamp.valueOf(bookingTime));
            pstmt.executeUpdate();

            // Update table status
            try (PreparedStatement updateTable = conn.prepareStatement(
                    "UPDATE tables SET status='booked' WHERE id=?")) {
                updateTable.setInt(1, table.getId());
                updateTable.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

