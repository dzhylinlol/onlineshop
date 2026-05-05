package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IProductImageDAO;
import com.solvd.onlineshop.model.ProductImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductImageDAO extends AbstractMySQLDAO implements IProductImageDAO {

    private static final Logger LOGGER = LogManager.getLogger(ProductImageDAO.class);

    @Override
    public ProductImage save(ProductImage pi) {
        String sql = "INSERT INTO product_images(product_id, imageUrl) VALUES (?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, pi.getProduct().getId());
                stm.setString(2, pi.getImageUrl());

                stm.executeUpdate();
                return pi;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving productImage", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(ProductImage pi) {
        String sql = "UPDATE product_images SET product_id = ?, imageUrl = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, pi.getProduct().getId());
                stm.setString(2, pi.getImageUrl());
                stm.setLong(3, pi.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No productImage found with id {}", pi.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating productImage", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public ProductImage getById(Long id) {
        String sql = "SELECT id, product_id, imageUrl FROM product_images WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        ProductImage pi = new ProductImage();
                        pi.setId(rs.getLong("id"));
                        pi.setImageUrl(rs.getString("imageUrl"));

                        ProductDAO productDAO = new ProductDAO();
                        pi.setProduct(productDAO.getById(rs.getLong("product_id")));

                        return pi;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching productImage by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM product_images WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No productImage found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting productImage with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}