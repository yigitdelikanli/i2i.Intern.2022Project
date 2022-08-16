package com.method;

import com.modul.usageMessage;
import com.modul.usageMessageConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

public class ConsumerMethod {
    public static void runConsumer(){
        Consumer<Long, usageMessage> consumer = usageMessageConsumer.createConsumer();
        Logger logger=LogManager.getLogger();

        while (true) {
            ConsumerRecords<Long, usageMessage> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            if (consumerRecords.count() == 0) {
                logger.info("No message to read");
                continue;
            }
            for (ConsumerRecord<Long, usageMessage> record : consumerRecords) {
                logger.info("Message received");
                logger.info(/*
                        "msisdn: "
                        + record.value().getMsisdn()
                        + " Used Amount: "
                        + record.value().getUsedAmount()
                        +" Used Service: "
                        + record.value().getUsedService().
                        + "(This record taken from Kafka)"*/
                        record.toString()
                );
                String msg =record.toString();
            }

            consumer.commitAsync();
        }
        //consumer.close();
    }
}
