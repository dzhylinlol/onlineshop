package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IProductDAO;
import com.solvd.onlineshop.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO extends AbstractMySQLDAO implements IProductDAO {

    private static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);

    @Override
    public Product save(Product p) {
        String sql = "INSERT INTO products(name, description, price, material, product_category_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, p.getName());
                stm.setString(2, p.getDescription());
                stm.setDouble(3, p.getPrice());
                stm.setString(4, p.getMaterial());
                stm.setLong(5, p.getProductCategory().getId());

                stm.executeUpdate();
                return p;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving product", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Product p) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, material = ?, product_category_id = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, p.getName());
                stm.setString(2, p.getDescription());
                stm.setDouble(3, p.getPrice());
                stm.setString(4, p.getMaterial());
                stm.setLong(5, p.getProductCategory().getId());
                stm.setLong(6, p.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No product found with id {}", p.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating product", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Product getById(Long id) {
        String sql = "SELECT id, name, description, price, material, product_category_id FROM products WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Product p = new Product();
                        p.setId(rs.getLong("id"));
                        p.setName(rs.getString("name"));
                        p.setDescription(rs.getString("description"));
                        p.setPrice(rs.getDouble("price"));
                        p.setMaterial(rs.getString("material"));

                        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
                        p.setProductCategory(productCategoryDAO.getById(rs.getLong("product_category_id")));

                        return p;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching product by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No product found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting product with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}