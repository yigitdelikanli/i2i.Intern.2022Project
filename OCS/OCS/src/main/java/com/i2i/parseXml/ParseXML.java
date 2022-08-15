package com.i2i.parseXml;

import com.i2i.model.Service;
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
import java.util.HashMap;
import java.util.Map;

public class ParseXML {

    private Map<String, Service> serviceMap = new HashMap<>();
    private DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    private Document document = builder.parse(new File("charging.xml"));

    public ParseXML() throws ParserConfigurationException, IOException, SAXException {

    }

    public Map<String,Service> getServiceRule(){

        NodeList nList = document.getElementsByTagName("service");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Service service = new Service();

                service.setRegex(Integer.parseInt(eElement
                        .getElementsByTagName("regex")
                        .item(0)
                        .getTextContent()));
                service.setRound(Integer.parseInt(eElement
                        .getElementsByTagName("round")
                        .item(0)
                        .getTextContent()));
                service.setPrice(Double.parseDouble(eElement
                        .getElementsByTagName("price")
                        .item(0)
                        .getTextContent()));

                serviceMap.put(eElement.getAttribute("serviceName"),service);

            }

        }
        return serviceMap;
    }
}
