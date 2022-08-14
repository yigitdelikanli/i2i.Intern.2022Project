package com.i2i;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.i2i.listenAkka.Listener;
import com.typesafe.config.ConfigFactory;

public class App {
    public static void main(String[] args) {

        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");
    }
}
