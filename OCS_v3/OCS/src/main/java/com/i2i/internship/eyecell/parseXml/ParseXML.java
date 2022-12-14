package com.i2i.internship.eyecell.parseXml;

import com.i2i.internship.eyecell.config.OCSConfig;
import com.i2i.internship.eyecell.model.Service;
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


public class ParseXML {

    private Logger logger = LogManager.getLogger(ParseXML.class);
    private List<Service> serviceList = new ArrayList<>();
    private DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    private Document document = builder.parse(new File(OCSConfig.getChargingFilePath())); // OCSConfig.getChargingFilePath() "charging.xml"

    public ParseXML() throws ParserConfigurationException, IOException, SAXException {

    }

    public List<Service> getServiceInfo(){
        NodeList nList = document.getElementsByTagName("service");
        logger.info("XML PARSING ");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Service service = new Service();

                service.setPackageName(eElement.getAttribute("serviceName"));
                service.setRegex(eElement
                        .getElementsByTagName("regex")
                        .item(0)
                        .getTextContent());
                service.setRound(Integer.parseInt(eElement
                        .getElementsByTagName("round")
                        .item(0)
                        .getTextContent()));
                service.setPrice(Double.parseDouble(eElement
                        .getElementsByTagName("price")
                        .item(0)
                        .getTextContent()));

                serviceList.add(service);
            }
        }
        return serviceList;
    }
}
