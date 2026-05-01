package com.solvd.onlineshop.dao.impl;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IBuyerDAO;
import com.solvd.onlineshop.model.Buyer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerDAO extends AbstractMySQLDAO implements IBuyerDAO {

    private static final Logger LOGGER = LogManager.getLogger(BuyerDAO.class);

    @Override
    public Buyer save(Buyer b) {
        String sql = "INSERT INTO buyers(firstName, lastName, email, phone, dob) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, b.getFirstName());
                stm.setString(2, b.getLastName());
                stm.setString(3, b.getEmail());
                stm.setString(4, b.getPhone());
                stm.setObject(5, b.getDob());

                stm.executeUpdate();
                return b;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving buyer", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Buyer b) {
        String sql = "UPDATE buyers SET firstName = ?, lastName = ?, email = ?, phone = ?, dob = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, b.getFirstName());
                stm.setString(2, b.getLastName());
                stm.setString(3, b.getEmail());
                stm.setString(4, b.getPhone());
                stm.setDate(5, java.sql.Date.valueOf(b.getDob()));

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No buyer found with id {}", b.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating buyer", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Buyer getById(Long id) {
        String sql = "SELECT id, firstName, lastName, email, phone, dob FROM buyers WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Buyer b = new Buyer();
                        b.setId(rs.getLong("id"));
                        b.setFirstName(rs.getString("firstName"));
                        b.setLastName(rs.getString("lastName"));
                        b.setEmail(rs.getString("email"));
                        b.setPhone(rs.getString("phone"));
                        b.setDob(rs.getDate("dob").toLocalDate());

                        return b;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching buyer by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM buyers WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No buyer found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting buyer with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}

