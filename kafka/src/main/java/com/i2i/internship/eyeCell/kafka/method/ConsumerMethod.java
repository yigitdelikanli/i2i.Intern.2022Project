package com.i2i.internship.eyeCell.kafka.method;

import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessageConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class ConsumerMethod {
    private static Logger logger = LogManager.getLogger(ConsumerMethod.class);

    public static void runConsumer() {
        Consumer<Long, UsageMessage> consumer = UsageMessageConsumer.createConsumer();

        while (true) {
            ConsumerRecords<Long, UsageMessage> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            if (consumerRecords.count() == 0) {
                logger.info("No message to read");
                continue;
            }
            for (ConsumerRecord<Long, UsageMessage> record : consumerRecords) {
                logger.info("Message received");
                logger.info(
                        record.toString()
                );
                UsageMessage msg = record.value();
                String msisdn =msg.getMsisdn();
                Long usedAmount= msg.getUsedAmount();
                String usedService= msg.getUsedService();
                System.out.println(msisdn);
                System.out.println(usedAmount);
                System.out.println(usedService);
            }

            consumer.commitAsync();
        }
        //consumer.close();
    }
}
