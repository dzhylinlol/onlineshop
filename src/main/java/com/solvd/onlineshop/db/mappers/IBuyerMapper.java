package com.solvd.onlineshop.db.mappers;

import com.solvd.onlineshop.model.Buyer;

public interface IBuyerMapper {

    void create(Buyer buyer);

    Buyer getById(Long id);

    void update(Buyer buyer);

    void delete(Long id);
}
