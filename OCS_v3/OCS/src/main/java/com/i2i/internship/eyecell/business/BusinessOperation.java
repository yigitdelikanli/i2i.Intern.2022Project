package com.i2i.internship.eyecell.business;

import com.i2i.internship.eyeCell.kafka.method.ProducerMethod;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyecell.voltDbProcess.VoltDbOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class BusinessOperation {
    private VoltDbOperation voltDbOperation;
    private PriceOperation priceOperation =new PriceOperation();
    private UsageMessage kafkaUsageMessage;
    private Logger log = LogManager.getLogger(BusinessOperation.class);
    private int priceVariable;

    public void connectionVoltdb(VoltDbOperation voltDbOperation){
        this.voltDbOperation =voltDbOperation;
    }

    public void start(String MSISDN,String location, String service ,int amount,String opNumber){
        log.info("START BUSINESS CLASS");
        priceVariable =0;

        if(service.equals("DATA")){
            amount = (int) Math.ceil(amount/1000000);
            System.out.println(amount);
        }
        if(service.equals("SMS")){
            amount = amount*10;
        }
        if(service.equals("VOICE")){
            amount = amount/60;
        }

        priceVariable = priceOperation.getUsedPrice(opNumber,amount,service);
        voltDBSender(MSISDN,service,amount,priceVariable);

        kafkaSender(MSISDN,service,amount,priceVariable);
    }


    public void voltDBSender(String MSISDN, String service ,int amount,int price){
        log.info("VOLTDB SEND METHOD START");
        if(service.toUpperCase().equals("SMS")){
          //  amount = amount*3;
            log.info("SMS data send voltdb from Business Class");
            voltDbOperation.sendSmsAmount(MSISDN,amount,price);
        }
        if(service.toUpperCase().equals("VOICE")){
            log.info("VOICE data send voltdb from Business Class");
            voltDbOperation.sendVoiceAmount(MSISDN,(int)(amount/60),price);
        }
        if(service.toUpperCase().equals("DATA")){
          /*
            if(amount>3000){
                amount=amount/1000000;
            }

           */
            log.info("DATA data send voltdb from Business Class");
            voltDbOperation.sendGbAmount(MSISDN,amount,price);
        }
    }

    public void kafkaSender(String MSISDN, String service ,int amount,int price){
        if(service.toUpperCase().equals("SMS")){
          //  amount = amount*3;
            kafkaUsageMessage = setKafkaMessage(MSISDN,service,amount,price);
        }
        if(service.toUpperCase().equals("VOICE")){
        //    amount = (int)amount/60;
            kafkaUsageMessage = setKafkaMessage(MSISDN,service,amount,price);
        }
        if(service.toUpperCase().equals("DATA")){
         /*
            if(amount>3000){
                amount=amount/1000000;
            }

          */
            kafkaUsageMessage = setKafkaMessage(MSISDN,service,amount,price);
        }
        log.info("Sending data to Kafka");
        try {
            ProducerMethod.runProducer(kafkaUsageMessage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public UsageMessage setKafkaMessage(String MSISDN, String service , int amount, int price){
        log.info("KAFKA SEND METHOD START");
        kafkaUsageMessage = new UsageMessage();
        int uid = (int) voltDbOperation.getUidByMSISDN(MSISDN);

        kafkaUsageMessage.setSubscriberID(uid);
        kafkaUsageMessage.setMsisdn(MSISDN);
        kafkaUsageMessage.setUsedAmount(amount);
        kafkaUsageMessage.setUsedService(service);
        kafkaUsageMessage.setPrice(price);

        return kafkaUsageMessage;

    }

}
