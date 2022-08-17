package com.i2i.internship.eyecell.business;

import com.i2i.internship.eyeCell.kafka.method.ProducerMethod;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyecell.voltDbProcess.VoltDbOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class BusinessOperation {
    private VoltDbOperation voltDbOperation = new VoltDbOperation();
    private PriceOperation priceOperation =new PriceOperation();
    private UsageMessage kafkaUsageMessage;
    private Logger log = LogManager.getLogger(BusinessOperation.class);
    private int priceVariable;


    public void start(String MSISDN,String location, String service ,int amount,String opNumber){

        priceVariable =0;
        priceVariable = priceOperation.getUsedPrice(MSISDN,amount,service);
        voltDBSender(MSISDN,service,amount,priceVariable);
        kafkaSender(MSISDN,service,amount,priceVariable);

    }


    public void voltDBSender(String MSISDN, String service ,int amount,int price){

        if(service =="SMS"){
            voltDbOperation.sendSmsAmount(MSISDN,amount,price);
            voltDbOperation.sendPrice(MSISDN,price);
        }
        if(service == "VOICE"){
            voltDbOperation.sendVoiceAmount(MSISDN,amount,price);
        }
        if(service =="DATA"){
            voltDbOperation.sendGbAmount(MSISDN,amount,price);
        }
    }
    // Burada mesajı tek nesne üzerinden göndermeye çalış
    public void kafkaSender(String MSISDN, String service ,int amount,int price){

        kafkaUsageMessage = new UsageMessage();
        int uid = (int) voltDbOperation.getUidByMSISDN(MSISDN);

        kafkaUsageMessage.setSubscriberID(uid);
        kafkaUsageMessage.setMsisdn(MSISDN);
        kafkaUsageMessage.setUsedAmount(amount);
        kafkaUsageMessage.setUsedService(service);
        kafkaUsageMessage.setPrice(price);

        log.info("Sending data to Kafka");
        try {
            ProducerMethod.runProducer(kafkaUsageMessage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
