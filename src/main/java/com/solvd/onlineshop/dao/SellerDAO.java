package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.model.Seller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerDAO extends AbstractMySQLDAO implements ISellerDAO {
    private static final Logger LOGGER = LogManager.getLogger(SellerDAO.class);

    @Override
    public Seller save(Seller s) {
        String sql = "INSERT INTO sellers(name, country, licenceNumber, email, phone) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, s.getName());
                stm.setString(2, s.getCountry());
                stm.setString(3, s.getLicenceNumber());
                stm.setString(4, s.getEmail());
                stm.setString(5, s.getPhone());

                stm.executeUpdate();
                return s;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving seller", e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void update(Seller s) {
        String sql = "UPDATE sellers SET name = ?, country = ?, licenceNumber = ?, email = ?, phone = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, s.getName());
                stm.setString(2, s.getCountry());
                stm.setString(3, s.getLicenceNumber());
                stm.setString(4, s.getEmail());
                stm.setString(5, s.getPhone());
                stm.setLong(6, s.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No seller found with id {}", s.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating seller", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Seller getById(Long id) {
        String sql = "SELECT id, name, country, licenceNumber, email, phone FROM sellers WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Seller s = new Seller();
                        s.setId(rs.getLong("id"));
                        s.setName(rs.getString("name"));
                        s.setCountry(rs.getString("country"));
                        s.setLicenceNumber(rs.getString("licenceNumber"));
                        s.setEmail(rs.getString("email"));
                        s.setPhone(rs.getString("phone"));

                        return s;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching seller by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM sellers WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No seller found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting seller with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}