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
    private Service voice118Service=null;
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
            voice118Service =serviceList.get(1);
            smsService =serviceList.get(2);
            gbService =serviceList.get(3);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public int getUsedPrice(String opNumber,int amount,String service){
        price =0;

        if(service.toUpperCase() =="SMS" && regexControl(opNumber,smsService.getRegex())){
            price = (int) (amount* smsService.getPrice());

        }
        if(service.toUpperCase() =="DATA" && regexControl(opNumber,gbService.getRegex())){
            price = (int) ((amount/gbService.getRound())* gbService.getPrice());

        }
        if(service.toUpperCase() =="VOICE" && regexControl(opNumber,voiceService.getRegex())){
            price = (int) ((amount/voiceService.getRound())* voiceService.getPrice());

        }
        if(service.toUpperCase() =="VOICE" && regexControl(opNumber,voice118Service.getRegex())){
            price = (int) ((amount/voice118Service.getRound())* voice118Service.getPrice());

        }
        return price;

    }

    private boolean regexControl(String opNumber,String regex){
        Pattern p = Pattern.compile(regex); //REGEX
        Matcher m = p.matcher(opNumber); //MSISDN

        return m.find();
    }
}
