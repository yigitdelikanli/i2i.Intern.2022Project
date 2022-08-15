package com.i2i.listenAkka;

import akka.actor.UntypedActor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.i2i.Message;


public class Listener extends UntypedActor {

    //   private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private Logger log = LogManager.getLogger();
    // private ActorRef loggingActor = getContext().actorOf(Props.create(LoggingActor.class), "LoggingActor");


    @Override
    public void onReceive(Object message) throws Exception {
        log.info("onReceive({})", message);

        if (message instanceof Message.Usage){
            log.info("MSISDN {}", ((Message.Usage) message).getMsisdn());
            log.info("Location {}", ((Message.Usage) message).getLocation());
            log.info("Service {}", ((Message.Usage) message).getService());
            log.info("Amount {}", ((Message.Usage) message).getAmount());
        }

    }

}
