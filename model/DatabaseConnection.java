package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/RMS";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    public static Connection getConnection() {
      try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to database successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed:");
            e.printStackTrace();
            return null;
        }
    }
}


