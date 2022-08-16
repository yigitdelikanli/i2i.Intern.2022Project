package com.i2i.listenAkka;

import akka.actor.UntypedActor;
import com.i2i.VoltDBProcess.VoltDbOperation;
import com.method.ProducerMethod;
import com.modul.usageMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.i2i.Message;

import java.util.concurrent.ExecutionException;


public class Listener extends UntypedActor {

    private VoltDbOperation operation = new VoltDbOperation();

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

            voltDBSender(((Message.Usage) message).getMsisdn(),((Message.Usage) message).getService(),((Message.Usage) message).getAmount());


        }

    }

    public void voltDBSender(String MSISDN, String service ,int amount){

        if(service =="sms"){
            operation.sendSmsAmount(MSISDN,amount);
        }
        if(service == "voice"){
            operation.sendVoiceAmount(MSISDN,amount);
        }
        if(service =="gb"){
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
