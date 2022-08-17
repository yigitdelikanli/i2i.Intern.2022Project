package com.i2i.internship.eyecell;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.i2i.internship.eyeCell.kafka.method.ProducerMethod;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyecell.config.OCSConfig;
import com.i2i.internship.eyecell.listenAkka.Listener;
import com.i2i.internship.eyecell.model.Service;
import com.i2i.internship.eyecell.parseXml.ParseXML;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class App {
    private static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

     //   fileControl(args);


/*
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");

*/
   //     kafkaSender("1234569877","voice",555);

     //   parseXml();


        log.info("Work");
    }

    public static void parseXml(){
        // Parse XML

        try {
            ParseXML parseXML =new ParseXML();
            Map<String , Service> serviceRule= parseXML.getServiceRule();

            for(Map.Entry<String,Service> m : serviceRule.entrySet()){
                log.info(m.getKey()+" Regex : " +m.getValue().getRegex());
                log.info(m.getKey()+" Round : " +m.getValue().getRound());
                log.info(m.getKey()+" Price : " +m.getValue().getPrice());
                /*
                System.out.println(m.getKey()+" Regex : " +m.getValue().getRegex());
                System.out.println(m.getKey()+" Round : " +m.getValue().getRound());
                System.out.println(m.getKey()+" Price : " +m.getValue().getPrice() + "\n");
*/
            }
        } catch (ParserConfigurationException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void kafkaSender(String MSISDN, String service ,int amount){

        UsageMessage message = new UsageMessage();

        message.setMsisdn(MSISDN);
        message.setUsedAmount(amount);
        message.setUsedService(service);

        log.info("Sending data to Kafka");

        try {
            ProducerMethod.runProducer(message);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void fileControl(String[] args){

        if(0<args[0].length()){
            log.info("File path read");
            OCSConfig.setChargingFilePath(args[0]);
        }else {
            log.error("File path read error");
        }
    }
}
