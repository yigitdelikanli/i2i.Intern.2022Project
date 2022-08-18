package com.i2i.internship.eyecell.listenAkka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.i2i.Message;
import com.i2i.internship.eyecell.business.BusinessOperation;


public class Listener extends UntypedActor {
    //private VoltDbOperation operation = new VoltDbOperation();
    private BusinessOperation businessOperation =new BusinessOperation();

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public void onReceive(Object message) throws Exception {
        log.info("onReceive({})", message);

        if (message instanceof Message.Usage){
            log.info("MSISDN {}", ((Message.Usage) message).getMsisdn());
            log.info("Location {}", ((Message.Usage) message).getLocation());
            log.info("Service {}", ((Message.Usage) message).getService());
            log.info("Amount {}", ((Message.Usage) message).getAmount());
         //   log.info("OpNumber {}", ((Message.Usage) message).getOpNumber());
/*
            businessOperation.start(
                    ((Message.Usage) message).getMsisdn(),
                    ((Message.Usage) message).getLocation(),
                    ((Message.Usage) message).getService(),
                    ((Message.Usage) message).getAmount(),
                    ((Message.Usage) message).getOpNumber()
                    );
*/
        }

    }
}
