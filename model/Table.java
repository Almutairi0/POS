package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private int id;
    private int capacity;
    private String status;

    public Table(int id, int capacity, String status) {
        this.id = id;
        this.capacity = capacity;
        this.status = status;
    }

    public int getId() { return id; }
    public int getCapacity() { return capacity; }
    public String getStatus() { return status; }

    public boolean isAvailable() {
        return status.equalsIgnoreCase("available");
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Table " + id + " (" + capacity + " seats) - " + status;
    }

    public static List<Table> loadTables() {
        List<Table> tables = new ArrayList<>();
        String sql = "SELECT * FROM tables";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tables.add(new Table(
                        rs.getInt("id"),
                        rs.getInt("capacity"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }
}

