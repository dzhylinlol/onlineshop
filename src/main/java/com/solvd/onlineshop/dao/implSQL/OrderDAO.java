package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IOrderDAO;
import com.solvd.onlineshop.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends AbstractMySQLDAO implements IOrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

    @Override
    public Order save(Order o) {
        String sql = "INSERT INTO orders(confirmationId, totalSum, seasonalDiscount, personalDiscount, seller_id, buyer_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, o.getConfirmationId());
                stm.setDouble(2, o.getTotalSum());
                stm.setDouble(3, o.getSeasonalDiscount());
                stm.setDouble(4, o.getPersonalDiscount());
                stm.setLong(5, o.getSeller().getId());
                stm.setLong(6, o.getBuyer().getId());

                stm.executeUpdate();
                return o;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving order", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Order o) {
        String sql = "UPDATE orders SET confirmationId = ?, totalSum = ?, seasonalDiscount = ?, personalDiscount = ?, seller_id = ?, buyer_id = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, o.getConfirmationId());
                stm.setDouble(2, o.getTotalSum());
                stm.setDouble(3, o.getSeasonalDiscount());
                stm.setDouble(4, o.getPersonalDiscount());
                stm.setLong(5, o.getSeller().getId());
                stm.setLong(6, o.getBuyer().getId());
                stm.setLong(7, o.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No order found with id {}", o.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating order", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Order getById(Long id) {
        String sql = "SELECT id, confirmationId, totalSum, seasonalDiscount, personalDiscount, seller_id, buyer_id FROM orders WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Order o = new Order();
                        o.setId(rs.getLong("id"));
                        o.setConfirmationId(rs.getString("confirmationId"));
                        o.setTotalSum(rs.getDouble("totalSum"));
                        o.setSeasonalDiscount(rs.getDouble("seasonalDiscount"));
                        o.setPersonalDiscount(rs.getDouble("personalDiscount"));

                        SellerDAO sellerDAO = new SellerDAO();
                        o.setSeller(sellerDAO.getById(rs.getLong("seller_id")));

                        BuyerDAO buyerDAO = new BuyerDAO();
                        o.setBuyer(buyerDAO.getById(rs.getLong("buyer_id")));

                        return o;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching order by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No order found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting order with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}
