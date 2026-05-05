package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IPersonalDiscountDAO;
import com.solvd.onlineshop.model.PersonalDiscount;
import com.solvd.onlineshop.model.PersonalDiscountReason;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDiscountDAO extends AbstractMySQLDAO implements IPersonalDiscountDAO {

    private static final Logger LOGGER = LogManager.getLogger(PersonalDiscountDAO.class);

    @Override
    public PersonalDiscount save(PersonalDiscount pd) {
        String sql = "INSERT INTO personal_discounts(personalDiscountReason, value) VALUES (?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, pd.getPersonalDiscountReason().name());
                stm.setDouble(2, pd.getValue());

                stm.executeUpdate();
                return pd;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving personalDiscount", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(PersonalDiscount pd) {
        String sql = "UPDATE personal_discounts SET personalDiscountReason = ?, value = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, pd.getPersonalDiscountReason().name());
                stm.setDouble(2, pd.getValue());
                stm.setLong(3, pd.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No personalDiscount found with id {}", pd.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating personalDiscount", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public PersonalDiscount getById(Long id) {
        String sql = "SELECT id, personalDiscountReason, value FROM personal_discounts WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        PersonalDiscount pd = new PersonalDiscount();
                        pd.setId(rs.getLong("id"));
                        pd.setPersonalDiscountReason(
                                PersonalDiscountReason.valueOf(rs.getString("personalDiscountReason"))
                        );
                        pd.setValue(rs.getDouble("value"));

                        return pd;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching personalDiscount by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM personal_discounts WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No personalDiscount found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting personalDiscount with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}