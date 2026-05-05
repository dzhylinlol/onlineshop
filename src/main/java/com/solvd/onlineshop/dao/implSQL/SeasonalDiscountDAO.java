package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.ISeasonalDiscountDAO;
import com.solvd.onlineshop.model.SeasonalDiscount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SeasonalDiscountDAO extends AbstractMySQLDAO implements ISeasonalDiscountDAO {

    private static final Logger LOGGER = LogManager.getLogger(SeasonalDiscountDAO.class);

    @Override
    public SeasonalDiscount save(SeasonalDiscount sd) {
        String sql = "INSERT INTO seasonal_discounts(value, effectiveFrom, effectiveTo, campaignId) VALUES (?, ?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setDouble(1, sd.getValue());
                stm.setTimestamp(2, Timestamp.valueOf(sd.getEffectiveFrom()));
                stm.setTimestamp(3, Timestamp.valueOf(sd.getEffectiveTo()));
                stm.setLong(4, sd.getCampaignId());

                stm.executeUpdate();
                return sd;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving seasonalDiscount", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(SeasonalDiscount sd) {
        String sql = "UPDATE seasonal_discounts SET value = ?, effectiveFrom = ?, effectiveTo = ?, campaignId = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setDouble(1, sd.getValue());
                stm.setTimestamp(2, Timestamp.valueOf(sd.getEffectiveFrom()));
                stm.setTimestamp(3, Timestamp.valueOf(sd.getEffectiveTo()));
                stm.setLong(4, sd.getCampaignId());
                stm.setLong(5, sd.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No seasonalDiscount found with id {}", sd.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating seasonalDiscount", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public SeasonalDiscount getById(Long id) {
        String sql = "SELECT id, value, effectiveFrom, effectiveTo, campaignId FROM seasonal_discounts WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        SeasonalDiscount sd = new SeasonalDiscount();
                        sd.setId(rs.getLong("id"));
                        sd.setValue(rs.getDouble("value"));
                        sd.setEffectiveFrom(rs.getTimestamp("effectiveFrom").toLocalDateTime());
                        sd.setEffectiveTo(rs.getTimestamp("effectiveTo").toLocalDateTime());
                        sd.setCampaignId(rs.getLong("campaignId"));

                        return sd;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching seasonalDiscount by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM seasonal_discounts WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No seasonalDiscount found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting seasonalDiscount with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}