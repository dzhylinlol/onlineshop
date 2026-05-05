package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IStorageDAO;
import com.solvd.onlineshop.model.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageDAO extends AbstractMySQLDAO implements IStorageDAO {

    private static final Logger LOGGER = LogManager.getLogger(StorageDAO.class);

    @Override
    public Storage save(Storage s) {
        String sql = "INSERT INTO storages(name, country) VALUES (?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, s.getName());
                stm.setString(2, s.getCountry());

                stm.executeUpdate();
                return s;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving storage", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Storage s) {
        String sql = "UPDATE storages SET name = ?, country = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, s.getName());
                stm.setString(2, s.getCountry());
                stm.setLong(3, s.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No storage found with id {}", s.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating storage", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Storage getById(Long id) {
        String sql = "SELECT id, name, country FROM storages WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Storage s = new Storage();
                        s.setId(rs.getLong("id"));
                        s.setName(rs.getString("name"));
                        s.setCountry(rs.getString("country"));

                        return s;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching storage by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM storages WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No storage found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting storage with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}