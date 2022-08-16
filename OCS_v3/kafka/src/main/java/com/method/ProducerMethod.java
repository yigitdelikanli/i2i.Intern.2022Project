package com.method;

import com.constants.kafkaConstants;
import com.modul.usageMessage;
import com.modul.usageMessageConsumer;
import com.modul.usageMessageProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;


public class ProducerMethod {
    public static void runProducer(usageMessage msg) throws ExecutionException, InterruptedException {
        Logger logger = LogManager.getLogger();
        Producer<Long, Serializable> producer = usageMessageProducer.createProducer();
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
    private static Serializable SerializeUsage(usageMessage msg){
        return msg;
    }

}

