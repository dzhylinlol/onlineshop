package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.ICampaignDAO;
import com.solvd.onlineshop.model.Campaign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CampaignDAO extends AbstractMySQLDAO implements ICampaignDAO {

    private static final Logger LOGGER = LogManager.getLogger(CampaignDAO.class);

    @Override
    public Campaign save(Campaign c) {
        String sql = "INSERT INTO campaigns(name) VALUES (?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, c.getName());

                stm.executeUpdate();
                return c;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving campaign", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Campaign c) {
        String sql = "UPDATE campaigns SET name = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, c.getName());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No campaign found with id {}", c.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating campaign", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Campaign getById(Long id) {
        String sql = "SELECT id, name WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Campaign c = new Campaign();
                        c.setId(rs.getLong("id"));
                        c.setName(rs.getString("name"));

                        return c;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching campaign by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM campaigns WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No campaign found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting campaign with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}
