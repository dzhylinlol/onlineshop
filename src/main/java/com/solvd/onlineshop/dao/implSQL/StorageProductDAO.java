package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IStorageProductDAO;
import com.solvd.onlineshop.model.StorageProduct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageProductDAO extends AbstractMySQLDAO implements IStorageProductDAO {

    private static final Logger LOGGER = LogManager.getLogger(StorageProductDAO.class);

    @Override
    public StorageProduct save(StorageProduct sp) {
        String sql = "INSERT INTO storage_products(storage_id, product_id, quantity) VALUES (?, ?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, sp.getStorage().getId());
                stm.setLong(2, sp.getProduct().getId());
                stm.setInt(3, sp.getQuantity());

                stm.executeUpdate();
                return sp;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving storageProduct", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(StorageProduct sp) {
        String sql = "UPDATE storage_products SET storage_id = ?, product_id = ?, quantity = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, sp.getStorage().getId());
                stm.setLong(2, sp.getProduct().getId());
                stm.setInt(3, sp.getQuantity());
                stm.setLong(4, sp.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No storageProduct found with id {}", sp.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating storageProduct", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public StorageProduct getById(Long id) {
        String sql = "SELECT id, storage_id, product_id, quantity FROM storage_products WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        StorageProduct sp = new StorageProduct();
                        sp.setId(rs.getLong("id"));
                        sp.setQuantity(rs.getInt("quantity"));

                        StorageDAO storageDAO = new StorageDAO();
                        sp.setStorage(storageDAO.getById(rs.getLong("storage_id")));

                        ProductDAO productDAO = new ProductDAO();
                        sp.setProduct(productDAO.getById(rs.getLong("product_id")));

                        return sp;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching storageProduct by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM storage_products WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No storageProduct found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting storageProduct with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}