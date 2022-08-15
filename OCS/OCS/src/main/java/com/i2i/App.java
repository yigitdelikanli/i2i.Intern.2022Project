package com.i2i;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.i2i.VoltDBProcess.VoltDbOperation;

import com.i2i.listenAkka.Listener;
import com.i2i.model.Service;
import com.i2i.parseXml.ParseXML;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.voltdb.client.ProcCallException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Logger log = LogManager.getLogger(App.class);


        // AKKA
  /*
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");
*/



        //  VoltDB
/*
        VoltDbOperation operation = new VoltDbOperation("34.159.58.171",49153);

        log.info(operation.getPackageName("5061176561"));

*/


        // Parse XML

        try {
            ParseXML parseXML =new ParseXML();
            Map<String , Service> serviceRule= parseXML.getServiceRule();

            for(Map.Entry<String,Service> m : serviceRule.entrySet()){
                System.out.println(m.getKey()+" Regex : " +m.getValue().getRegex());
                System.out.println(m.getKey()+" Round : " +m.getValue().getRound());
                System.out.println(m.getKey()+" Price : " +m.getValue().getPrice() + "\n");

            }
        } catch (ParserConfigurationException e) {
            // logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            //  logger.error(e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            //  logger.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
