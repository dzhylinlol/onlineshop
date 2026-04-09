package com.solvd.onlineshop.dao;

import java.sql.Connection;

public abstract class AbstractMySQLDAO {

    protected Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    protected void releaseConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
