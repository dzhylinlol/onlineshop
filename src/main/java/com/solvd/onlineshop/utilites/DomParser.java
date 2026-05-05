package com.solvd.onlineshop.utilites;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser<T> {

    private static final Logger LOGGER = LogManager.getLogger(DomParser.class);

    private final ElementMapper<T> mapper;

    public DomParser(ElementMapper<T> mapper) {
        this.mapper = mapper;
    }

    public interface ElementMapper<T> {
        T map(Element element);
    }

    public List<T> parse(String filePath, String tagName)
            throws ParserConfigurationException, SAXException, IOException {

        List<T> results = new ArrayList<>();

        File file = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        document.getDocumentElement().normalize();

        NodeList nodes = document.getElementsByTagName(tagName);

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                T object = mapper.map(element);
                results.add(object);
            }
        }

        return results;
    }

    public static String getTagValue(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list == null || list.getLength() == 0) {
            LOGGER.warn("Tag '{}' not found", tagName);
            return null;
        }
        return list.item(0).getTextContent();
    }
}