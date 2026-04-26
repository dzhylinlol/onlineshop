package com.solvd.onlineshop.utilites;

import com.solvd.onlineshop.model.Seller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SellerDomParser {
    private static final Logger LOGGER = LogManager.getLogger(SellerDomParser.class);

    public static List<Seller> parseSellers(String filePath) {
        List<Seller> sellers = new ArrayList<>();

        try {
            File file = new File(filePath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            NodeList sellerNodes = document.getElementsByTagName("seller");

            for (int i = 0; i < sellerNodes.getLength(); i++) {
                Node node = sellerNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Seller seller = new Seller();
                    seller.setId(Long.parseLong(element.getAttribute("id")));
                    seller.setName(getTagValue(element, "name"));
                    seller.setCountry(getTagValue(element, "country"));
                    seller.setLicenceNumber(getTagValue(element, "licenceNumber"));
                    seller.setEmail(getTagValue(element, "email"));
                    seller.setPhone(getTagValue(element, "phone"));

                    sellers.add(seller);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Failed to parse sellers from XML file: {}", filePath, e);
        }

        return sellers;
    }

    private static String getTagValue(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).item(0).getTextContent();
    }
}

