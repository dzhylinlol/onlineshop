package com.solvd.onlineshop;

import com.solvd.onlineshop.model.Seller;
import com.solvd.onlineshop.utilites.JaxbUtil;
import com.solvd.onlineshop.utilites.SellerDomParser;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("DOM Parsing Sellers");

        List<Seller> sellers = SellerDomParser.parseSellers("src/main/resources/sellers.xml");

        if (sellers.isEmpty()) {
            LOGGER.error("No seller elements found in sellers.xml");
        } else {
            LOGGER.info("DOM parsing completed successfully. Number of sellers parsed: {}", sellers.size());
        }

        LOGGER.info("JAXB Parsing Sellers");

        String filePath = "src/main/resources/seller_jaxb.xml";

        Seller seller = new Seller();
        seller.setId(1L);
        seller.setName("LOL");
        seller.setCountry("Belarus");
        seller.setLicenceNumber("LIC-123");
        seller.setEmail("lol@example.com");
        seller.setPhone("+123456789");

        try {
            JaxbUtil.marshal(seller, filePath);
            LOGGER.info("Seller marshalled to XML");

            Seller unmarshalledSeller = JaxbUtil.unmarshal(filePath, Seller.class);
            LOGGER.info("Unmarshalled seller: {}", unmarshalledSeller);

        } catch (JAXBException e) {
            LOGGER.error("JAXB error occurred", e);
        }
    }
}


