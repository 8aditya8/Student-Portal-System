// src/main/java/edu/portal/dao/Database.java
package edu.portal.dao;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:portal.db";
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    private static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            createTables(conn);
            insertDefaultUsers(conn);
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL,
                role TEXT NOT NULL CHECK (role IN ('ADMIN', 'STUDENT', 'INSTRUCTOR'))
            )
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
        }
    }

    private static void insertDefaultUsers(Connection conn) throws SQLException {
        String checkAdmin = "SELECT COUNT(*) FROM users WHERE role = 'ADMIN'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkAdmin)) {
            
            if (rs.next() && rs.getInt(1) == 0) {
                String insertAdmin = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertAdmin)) {
                    pstmt.setString(1, "admin");
                    pstmt.setString(2, "admin123");
                    pstmt.setString(3, "ADMIN");
                    pstmt.executeUpdate();
                }

                // Insert sample users
                String[] sampleUsers = {
                    "INSERT INTO users (username, password, role) VALUES ('student1', 'pass123', 'STUDENT')",
                    "INSERT INTO users (username, password, role) VALUES ('instructor1', 'pass123', 'INSTRUCTOR')",
                    "INSERT INTO users (username, password, role) VALUES ('john_doe', 'password', 'STUDENT')",
                    "INSERT INTO users (username, password, role) VALUES ('jane_smith', 'password', 'INSTRUCTOR')"
                };

                try (Statement sampleStmt = conn.createStatement()) {
                    for (String sql : sampleUsers) {
                        sampleStmt.execute(sql);
                    }
                }
            }
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}