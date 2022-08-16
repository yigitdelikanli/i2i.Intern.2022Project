package com.i2i;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.i2i.listenAkka.Listener;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public static void main(String[] args) {


        Logger log = LogManager.getLogger(App.class);
        log.info("Selam brooooo");

        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");
    }
}
