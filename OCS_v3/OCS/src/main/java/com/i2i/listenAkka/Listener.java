package com.i2i.listenAkka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.i2i.Message;
import com.i2i.voltDbProcess.VoltDbOperation;
import com.method.ProducerMethod;
import com.modul.usageMessage;

import java.util.concurrent.ExecutionException;


public class Listener extends UntypedActor {
    private VoltDbOperation operation = new VoltDbOperation();

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("onReceive({})", message);

        if (message instanceof Message.Usage){
            log.info("MSISDN {}", ((Message.Usage) message).getMsisdn());
            log.info("Location {}", ((Message.Usage) message).getLocation());
            log.info("Service {}", ((Message.Usage) message).getService());
            log.info("Amount {}", ((Message.Usage) message).getAmount());

            //     voltDBSender(((Message.Usage) message).getMsisdn(),((Message.Usage) message).getService(),((Message.Usage) message).getAmount());

        }

    }


    public void voltDBSender(String MSISDN, String service ,int amount){

        if(service =="SMS"){
            operation.sendSmsAmount(MSISDN,amount);
        }
        if(service == "VOICE"){
            operation.sendVoiceAmount(MSISDN,amount);
        }
        if(service =="DATA"){
            operation.sendGbAmount(MSISDN,amount);
        }
    }

    public void kafkaSender(String MSISDN, String service ,int amount){

        usageMessage message = new usageMessage();

        message.setMsisdn("23424324234");
        message.setUsedAmount(123);
        message.setUsedService("sms");

        try {
            ProducerMethod.runProducer(message);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
