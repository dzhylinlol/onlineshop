package com.solvd.onlineshop.model;

import java.time.LocalDateTime;

public class Shipment {
    private Long id;
    private ShipmentStatus shipmentStatus;
    private LocalDateTime deliveredAt;

    public Shipment() {
    }

    public Shipment(Long id,
                    ShipmentStatus shipmentStatus,
                    LocalDateTime deliveredAt) {
        this.id = id;
        this.shipmentStatus = shipmentStatus;
        this.deliveredAt = deliveredAt;
    }

    @Override
    public String toString() {
        return "Shipment(id=" + this.getId() + ", shipmentStatus=" + this.getShipmentStatus() + ", deliveredAt=" + this.getDeliveredAt() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public ShipmentStatus getShipmentStatus() {
        return this.shipmentStatus;
    }

    public LocalDateTime getDeliveredAt() {
        return this.deliveredAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

}
