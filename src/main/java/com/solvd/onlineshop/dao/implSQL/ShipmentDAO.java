package com.solvd.onlineshop.dao.implSQL;

import com.solvd.onlineshop.dao.AbstractMySQLDAO;
import com.solvd.onlineshop.dao.IShipmentDAO;
import com.solvd.onlineshop.model.Shipment;
import com.solvd.onlineshop.model.ShipmentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ShipmentDAO extends AbstractMySQLDAO implements IShipmentDAO {

    private static final Logger LOGGER = LogManager.getLogger(ShipmentDAO.class);

    @Override
    public Shipment save(Shipment s) {
        String sql = "INSERT INTO shipments(shipmentStatus, deliveredAt) VALUES (?, ?)";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, s.getShipmentStatus().name());
                stm.setTimestamp(2, Timestamp.valueOf(s.getDeliveredAt()));

                stm.executeUpdate();
                return s;
            }

        } catch (SQLException e) {
            LOGGER.error("Error saving shipment", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void update(Shipment s) {
        String sql = "UPDATE shipments SET shipmentStatus = ?, deliveredAt = ? WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, s.getShipmentStatus().name());
                stm.setTimestamp(2, Timestamp.valueOf(s.getDeliveredAt()));
                stm.setLong(3, s.getId());

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No shipment found with id {}", s.getId());
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating shipment", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public Shipment getById(Long id) {
        String sql = "SELECT id, shipmentStatus, deliveredAt FROM shipments WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Shipment s = new Shipment();
                        s.setId(rs.getLong("id"));
                        s.setShipmentStatus(ShipmentStatus.valueOf(rs.getString("shipmentStatus")));
                        s.setDeliveredAt(rs.getTimestamp("deliveredAt").toLocalDateTime());

                        return s;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error fetching shipment by id {}", id, e);
        } finally {
            releaseConnection(con);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM shipments WHERE id = ?";
        Connection con = null;

        try {
            con = getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                int rows = stm.executeUpdate();

                if (rows == 0) {
                    LOGGER.warn("No shipment found to delete with id {}", id);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting shipment with id {}", id, e);
        } finally {
            releaseConnection(con);
        }
    }
}