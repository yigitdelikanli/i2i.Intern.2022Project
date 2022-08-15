package com.i2i;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class ClientActor  extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    // Getting the other actor          //CalculatorActor
    private ActorSelection selection = getContext().actorSelection("akka.tcp://AkkaRemoteServer@127.0.0.1:2552/user/Listener");


    @Override
    public void onReceive(Object message) throws Exception {
        if (message.equals("DoCalcs")) {

            log.info("Got a calc job, send it to the remote calculator");
            selection.tell(new Message.Sum(2, 2), getSelf());

        } else if (message instanceof Message.Result) {
            Message.Result result = (Message.Result) message;
            log.info("Got result back from calculator: {}", result.getResult());
        }
        else{
            selection.tell(new Message.Usage("542", "TURKEY", "DATA", 500), getSelf());
        }


    }
}
