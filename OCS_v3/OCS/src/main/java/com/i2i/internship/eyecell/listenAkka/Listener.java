package com.i2i.internship.eyecell.listenAkka;

import akka.actor.UntypedActor;
import com.i2i.Message;
import com.i2i.internship.eyecell.business.BusinessOperation;
import com.i2i.internship.eyecell.voltDbProcess.VoltDbOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listener extends UntypedActor {
    private BusinessOperation businessOperation =new BusinessOperation();
    private VoltDbOperation voltDbOperation = new VoltDbOperation();
    private Logger log = LogManager.getLogger(Listener.class);

    public Listener(){
        log.info("VoltDb connection in Listener Constructor");
        this.businessOperation.connectionVoltdb(voltDbOperation);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("onReceive({})", message);

        if (message instanceof Message.Usage){
            log.info("LISTENER GETTING DATA");
            log.info("MSISDN {}", ((Message.Usage) message).getMsisdn());
            log.info("Location {}", ((Message.Usage) message).getLocation());
            log.info("Service {}", ((Message.Usage) message).getService());
            log.info("Amount {}", ((Message.Usage) message).getAmount());
            log.info("OpNumber {}", ((Message.Usage) message).getOpNumber());

            businessOperation.start(
                    ((Message.Usage) message).getMsisdn(),
                    ((Message.Usage) message).getLocation(),
                    ((Message.Usage) message).getService(),
                    ((Message.Usage) message).getAmount(),
                    ((Message.Usage) message).getOpNumber()
                    );

        }
    }

}
