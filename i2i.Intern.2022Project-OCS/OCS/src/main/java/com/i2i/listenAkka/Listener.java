package com.i2i.listenAkka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.i2i.Message;
import com.sun.prism.Texture;
//import example.akka.remote.shared.LoggingActor;
//import example.akka.remote.shared.Messages;


public class Listener extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    //private ActorRef loggingActor = getContext().actorOf(Props.create(LoggingActor.class), "LoggingActor");

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("onReceive({})", message);

        /*if (message instanceof Messages.Sum) {
            log.info("got a Sum message");
            Messages.Sum sum = (Messages.Sum) message;

            int result = sum.getFirst() + sum.getSecond();
            getSender().tell(new Messages.Result(result), getSelf());

            loggingActor.tell(sum.getFirst() + " + " + sum.getSecond() + " = " + result, getSelf());
        } else {
            unhandled(message);
        }*/
        if (message instanceof Message.Usage){
            log.info("MSISDN {}", ((Message.Usage) message).getMsisdn());
            log.info("Location {}", ((Message.Usage) message).getLocation());
            log.info("Service {}", ((Message.Usage) message).getService());
            log.info("Amount {}", ((Message.Usage) message).getAmount());
        }



        /*
        log.info("onReceive({})", message);
        System.out.println("mesaj geldi : "+ message);      // sonradan

        if (message instanceof String) {
            log.info("got a Sum message");
            log.info((String) message);
            getSender().tell(message, getSelf());
        }
        else {
            unhandled(message);
            System.out.println(message);                     // sonradan
        }*/
    }
}
