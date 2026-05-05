package com.solvd.onlineshop;

import com.solvd.onlineshop.model.Buyer;
import com.solvd.onlineshop.model.Seller;
import com.solvd.onlineshop.utilites.JacksonUtil;
import com.solvd.onlineshop.utilites.JaxbUtil;
import com.solvd.onlineshop.utilites.DomParser;
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
            DomParser<Seller> parser = new DomParser<>(element -> {
                Seller seller = new Seller();
                seller.setId(Long.parseLong(element.getAttribute("id")));
                seller.setName(com.solvd.onlineshop.utilites.DomParser.getTagValue(element, "name"));
                seller.setCountry(com.solvd.onlineshop.utilites.DomParser.getTagValue(element, "country"));
                seller.setLicenceNumber(DomParser.getTagValue(element, "licenceNumber"));
                seller.setEmail(DomParser.getTagValue(element, "email"));
                seller.setPhone(com.solvd.onlineshop.utilites.DomParser.getTagValue(element, "phone"));
                return seller;
            });

            List<Seller> sellers = parser.parse("src/main/resources/sellers.xml", "seller");
            sellers.forEach(seller -> LOGGER.info("Parsed seller: {}", seller));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Error parsing sellers XML", e);
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


