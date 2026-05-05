package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IProductCategoryDAO;
import com.solvd.onlineshop.model.ProductCategory;
import com.solvd.onlineshop.model.ProductCategoryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryDAO extends AbstractMySQLDAO implements IProductCategoryDAO {

    private static final Logger LOGGER = LogManager.getLogger(ProductCategoryDAO.class);

    @Override
    public ProductCategory save(ProductCategory pc) {
        String sql = "INSERT INTO product_categories(productCategoryType) VALUES (?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, pc.getProductCategoryType().name());

                stm.executeUpdate();
                return pc;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving productCategory", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(ProductCategory pc) {
        String sql = "UPDATE product_categories SET productCategoryType = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, pc.getProductCategoryType().name());
                stm.setLong(2, pc.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No productCategory found with id {}", pc.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating productCategory", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public ProductCategory getById(Long id) {
        String sql = "SELECT id, productCategoryType FROM product_categories WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        ProductCategory pc = new ProductCategory();
                        pc.setId(rs.getLong("id"));
                        pc.setProductCategoryType(
                                ProductCategoryType.valueOf(rs.getString("productCategoryType"))
                        );

                        return pc;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching productCategory by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM product_categories WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No productCategory found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting productCategory with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}