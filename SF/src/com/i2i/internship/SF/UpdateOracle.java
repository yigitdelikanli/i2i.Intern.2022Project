package com.i2i.internship.SF;
	
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessageConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.time.Duration;

public class ConsumerMethod {
    private static Logger logger = LogManager.getLogger(ConsumerMethod.class);

    public static void runConsumer() throws SQLException {
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
                int price =msg.getPrice();
                int subscriberID= msg.getSubscriberID();



                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@34.77.240.18:49161:xe","eyecell","12345");
                System.out.println("Oracle Database connected");//log

                CallableStatement callableStatement = connection.prepareCall( "{call package_dmloperations.update_data(?,?,?,?)}");
                callableStatement.setInt(1,  subscriberID);
                callableStatement.setString(2,msisdn );
                callableStatement.setLong(3,  usedAmount);
                callableStatement.setInt(4,  price);



                callableStatement.execute();
                System.out.println("Datas sent");//log






                System.out.println(msisdn);
                System.out.println(usedAmount);
                System.out.println(usedService);
                System.out.println(price);
                System.out.println(subscriberID);

            }

            consumer.commitAsync();
        }
        //consumer.close();
    }
}
