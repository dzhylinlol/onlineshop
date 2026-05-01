package com.solvd.onlineshop;

import com.solvd.onlineshop.model.Buyer;
import com.solvd.onlineshop.model.Seller;
import com.solvd.onlineshop.utilites.JacksonUtil;
import com.solvd.onlineshop.utilites.JaxbUtil;
import com.solvd.onlineshop.utilites.SellerDomParser;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("DOM Parsing Sellers");

        try {
            List<Seller> sellers =
                    SellerDomParser.parseSellers("src/main/resources/sellers.xml");
            if (sellers.isEmpty()) {
                LOGGER.warn("No seller elements found in sellers.xml");
            } else {
                LOGGER.info("Parsed {} sellers", sellers.size());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Error while parsing sellers.xml", e);
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

        LOGGER.info("Jackson Parsing Buyer");

        String filePath1 = "buyer.json";

        Buyer buyer = new Buyer(
                1L,
                "John",
                "Doe",
                "john@example.com",
                "+123456789",
                LocalDate.of(1990, 5, 15)
        );

        try {
            JacksonUtil.serialize(buyer, filePath1);
            LOGGER.info("Buyer serialized to JSON");

            Buyer parsedBuyer = JacksonUtil.deserialize(filePath1, Buyer.class);
            LOGGER.info("Deserialized buyer: {}", parsedBuyer);

        } catch (IOException e) {
            LOGGER.error("Jackson error occurred", e);
        }
    }
}


