package com.i2i;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.com.i2i.intern.EyeCell.HazelcastConfiguration;

import java.util.Random;

public class Client {
    public static final Logger logger = LogManager.getLogger(Client.class);
    public static void main(String[] args) {
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteClient", ConfigFactory.load());

        // Client actor
        ActorRef client = system.actorOf(Props.create(ClientActor.class));

        HazelcastConfiguration hazelcastConfiguration = new HazelcastConfiguration();
        hazelcastConfiguration.initConnection("34.77.94.205", "34.77.94.205:5702");
        String msisdn = hazelcastConfiguration.getMsisdn(444);
        // System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+msisdn);


        /*MSISDNGenerator msisdnGenerator = new MSISDNGenerator();
        LocationGenerator locationGenerator = new LocationGenerator();
        ServiceGenerator serviceGenerator = new ServiceGenerator();
        AmountGenerator amountGenerator = new AmountGenerator();*/
        Random rand = new Random();
        while(true) {
            if(rand.nextInt()%100000000!=0){ //randomly select 1 of 5 cycles
                continue;
            }
            /*String msisdn = msisdnGenerator.getMsisdn();
            String location = locationGenerator.getLocation();
            String service = serviceGenerator.getService();
            int amount = amountGenerator.getAmount(service);
            logger.info("MSISDN: " + msisdn);
            logger.warn("Location: " + location);
            logger.error("Service: " + service);
            logger.fatal("Amount: " + amount);*/
            //client.tell("DoCalcs", ActorRef.noSender());
            client.tell(msisdn, ActorRef.noSender());
        }

        // Send a Calc job

    }
}
