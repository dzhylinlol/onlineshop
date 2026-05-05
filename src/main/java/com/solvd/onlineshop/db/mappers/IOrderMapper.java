package com.solvd.onlineshop.db.mappers;

import com.solvd.onlineshop.model.Order;
import com.solvd.onlineshop.model.OrderProduct;

import java.util.List;

public interface IOrderMapper {

    void create(Order order);

    Order getById(Long id);

    void update(Order order);

    void delete(Long id);

    List<OrderProduct> getItemsByOrderId(Long orderId);
}

