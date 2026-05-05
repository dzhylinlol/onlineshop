package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IReviewDAO;
import com.solvd.onlineshop.model.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReviewDAO extends AbstractMySQLDAO implements IReviewDAO {

    private static final Logger LOGGER = LogManager.getLogger(ReviewDAO.class);

    @Override
    public Review save(Review r) {
        String sql = "INSERT INTO reviews(product_id, buyer_id, rating, comment, createdAt) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, r.getProduct().getId());
                stm.setLong(2, r.getBuyer().getId());
                stm.setInt(3, r.getRating());
                stm.setString(4, r.getComment());
                stm.setTimestamp(5, Timestamp.valueOf(r.getCreatedAt()));

                stm.executeUpdate();
                return r;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving review", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Review r) {
        String sql = "UPDATE reviews SET product_id = ?, buyer_id = ?, rating = ?, comment = ?, createdAt = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, r.getProduct().getId());
                stm.setLong(2, r.getBuyer().getId());
                stm.setInt(3, r.getRating());
                stm.setString(4, r.getComment());
                stm.setTimestamp(5, Timestamp.valueOf(r.getCreatedAt()));
                stm.setLong(6, r.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No review found with id {}", r.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating review", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Review getById(Long id) {
        String sql = "SELECT id, product_id, buyer_id, rating, comment, createdAt FROM reviews WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Review r = new Review();
                        r.setId(rs.getLong("id"));
                        r.setRating(rs.getInt("rating"));
                        r.setComment(rs.getString("comment"));
                        r.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());

                        ProductDAO productDAO = new ProductDAO();
                        r.setProduct(productDAO.getById(rs.getLong("product_id")));

                        BuyerDAO buyerDAO = new BuyerDAO();
                        r.setBuyer(buyerDAO.getById(rs.getLong("buyer_id")));

                        return r;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching review by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM reviews WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No review found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting review with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}