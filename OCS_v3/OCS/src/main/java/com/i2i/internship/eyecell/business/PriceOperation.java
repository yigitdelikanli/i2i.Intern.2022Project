package com.i2i.internship.eyecell.business;

import com.i2i.internship.eyecell.App;
import com.i2i.internship.eyecell.model.Service;
import com.i2i.internship.eyecell.parseXml.ParseXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceOperation {
    private Service smsService=null;
    private Service gbService =null;
    private Service voiceService=null;
    private int price;

    private static Logger log = LogManager.getLogger(App.class);

    public PriceOperation(){
        assignmentService();
    }

    private void assignmentService(){
        try {
            ParseXML parseXML =new ParseXML();
            List<Service> serviceList = parseXML.getServiceInfo();
            voiceService =serviceList.get(0);
            smsService =serviceList.get(1);
            gbService =serviceList.get(2);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public int getUsedPrice(String MSISDN,int amount,String service){
        price =0;

        if(service =="sms" && regexControl(MSISDN,smsService.getRegex())){
            price = (int) ((amount/smsService.getRound())* smsService.getPrice());

        }
        if(service =="data" && regexControl(MSISDN,gbService.getRegex())){
            price = (int) ((amount/gbService.getRound())* gbService.getPrice());

        }
        if(service =="voice" && regexControl(MSISDN,voiceService.getRegex())){
            price = (int) ((amount/voiceService.getRound())* voiceService.getPrice());

        }
        return price;

    }

    private boolean regexControl(String MSISDN,String regex){
        Pattern p = Pattern.compile("[^505][0-9]{5}$"); //REGEX
        Matcher m = p.matcher("5051234545"); //MSISDN

        return m.find();
    }
}
