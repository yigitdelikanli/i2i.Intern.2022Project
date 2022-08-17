package com.i2i.internship.eyecell;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public static void main(String[] args) {


        Logger log = LogManager.getLogger(App.class);
/*
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");

 */
        log.info("Work");
    }
}
