package com.solvd.onlineshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    private static final int POOL_SIZE = 10;

    private final Queue<Connection> availableConnections = new LinkedList<>();

    private static ConnectionPool instance;

    private ConnectionPool() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                availableConnections.offer(createConnection());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public synchronized Connection getConnection() {
        try {
            if (availableConnections.isEmpty()) {
                return createConnection();
            }
            return availableConnections.poll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            availableConnections.offer(connection);
        }
    }
}