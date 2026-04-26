package com.solvd.onlineshop;

import com.solvd.onlineshop.model.Seller;
import com.solvd.onlineshop.utilites.SellerDomParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Seller> sellers = SellerDomParser.parseSellers("src/main/resources/sellers.xml");

        for (Seller seller : sellers) {
            LOGGER.info("ID: {}", seller.getId());
            LOGGER.info("Name: {} ", seller.getName());
            LOGGER.info("Country: {}", seller.getCountry());
            LOGGER.info("Licence: {}", seller.getLicenceNumber());
            LOGGER.info("Email: {}", seller.getEmail());
            LOGGER.info("Phone: {}", seller.getPhone());
            LOGGER.info("---------New Seller----------");
        }
    }
}


