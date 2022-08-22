package com.i2i.internship.eyecell;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.i2i.internship.eyecell.config.OCSConfig;
import com.i2i.internship.eyecell.listenAkka.Listener;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    private static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        fileControl(args);
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());
        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");
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
