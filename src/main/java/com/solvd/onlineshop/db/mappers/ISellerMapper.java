package com.solvd.onlineshop.db.mappers;

import com.solvd.onlineshop.model.Seller;

public interface ISellerMapper {

    void create(Seller seller);

    Seller getById(Long id);

    Seller getByLicenceNumber(String licenceNumber);

    void update(Seller seller);

    void delete(Long id);
}

