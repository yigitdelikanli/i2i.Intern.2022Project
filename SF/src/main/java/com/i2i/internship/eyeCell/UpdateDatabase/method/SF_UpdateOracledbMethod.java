package com.i2i.internship.eyeCell.UpdateDatabase.method;

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
import java.util.TimeZone;

public class SF_UpdateOracledbMethod {

    public static Connection getOracle() throws Exception {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        TimeZone.setDefault(timeZone);
        Connection conn = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@34.77.240.18:49161:xe", "eyecell", "12345");
        return conn;
    }
    private static Logger logger = LogManager.getLogger(SF_UpdateOracledbMethod.class);

    public static void runConsumer() throws Exception {
        Consumer<Long, UsageMessage> consumer = UsageMessageConsumer.createConsumer();

        while (true) {
            ConsumerRecords<Long, UsageMessage> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            if (consumerRecords.count() == 0) {
                logger.info("No message to read");
                continue;
            }
            for (ConsumerRecord<Long, UsageMessage> record : consumerRecords) {
                logger.info("Message received");
                logger.info(record.toString() );
                UsageMessage msg = record.value();
                String msisdn =msg.getMsisdn();
                long usedAmount= msg.getUsedAmount();
                String usedService= msg.getUsedService();
                long price =msg.getPrice();
                long subscriberID= msg.getSubscriberID();



                Connection connection = getOracle();
                TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
                TimeZone.setDefault(timeZone);
                logger.info("Oracle Database connected");
                //Be careful from here

                CallableStatement callableStatement = null;
                if(usedService.equalsIgnoreCase("data")){
                    logger.info("------> DATA <------");
                    callableStatement = connection.prepareCall( "{call package_dmloperations.update_data(?,?,?,?)}");
                    callableStatement.setLong(1,  subscriberID);
                    callableStatement.setString(2,msisdn );
                    callableStatement.setLong(3,  usedAmount);
                    callableStatement.setLong(4,  price);

                    callableStatement.execute();
                    logger.info("Datas sent to Oracle Database");
                }
                else if (usedService.equalsIgnoreCase("voice")){
                    logger.info("------> VOICE <------");
                    callableStatement = connection.prepareCall( "{call package_dmloperations.update_voice(?,?,?,?)}");
                    callableStatement.setLong(1,  subscriberID);
                    callableStatement.setString(2,msisdn );
                    callableStatement.setLong(3,  usedAmount);
                    callableStatement.setLong(4,  price);

                    callableStatement.execute();
                    logger.info("Datas sent to Oracle Database");
                }
                else if(usedService.equalsIgnoreCase("sms")){
                    logger.info("------> SMS <------");
                    callableStatement = connection.prepareCall( "{call package_dmloperations.update_sms(?,?,?,?)}");
                    callableStatement.setLong(1,  subscriberID);
                    callableStatement.setString(2,msisdn );
                    callableStatement.setLong(3,  usedAmount);
                    callableStatement.setLong(4,  price);

                    callableStatement.execute();
                    logger.info("Datas sent to Oracle Database");
                }

                callableStatement.close();
                connection.close();
                Thread.sleep(100);
/*
                switch (usedService){
                    case "Data":
                        break;
                    case "Voice":
                        //CallableStatement
                        break;
                    case "Sms":
                        //CallableStatement
                        break;
                }*/
/*
                System.out.println(msisdn);
                System.out.println(usedAmount);
                System.out.println(usedService);
                System.out.println(price);
                System.out.println(subscriberID);
 */

            }

            consumer.commitAsync();
        }
        //consumer.close();
    }
}