package com.i2i.internship.eyeCell.kafka.method;

import com.i2i.internship.eyeCell.kafka.constants.kafkaConstants;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessageProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;


public class ProducerMethod {
    private static Logger logger=LogManager.getLogger(ProducerMethod.class);
    public static void runProducer(UsageMessage msg) throws ExecutionException, InterruptedException {

        Producer<Long, Serializable> producer = UsageMessageProducer.createProducer();
        logger.info("Producer created.");
        ProducerRecord<Long,Serializable> record = new ProducerRecord<>(kafkaConstants.topicName,msg);
        try{
        producer.send(record).get();
        logger.info("Message Send");
        }
        catch (ExecutionException e){
            logger.info("Error during producer send : ");
            logger.info(e);
        }
        catch (InterruptedException e){
            logger.info("Error during producer send : ");
            logger.info(e);
        }
    }
    private static Serializable SerializeUsage(UsageMessage msg){
        return msg;
    }

}

