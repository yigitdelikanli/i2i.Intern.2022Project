package com.modul;
import java.nio.ByteBuffer;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class usageMessageDeserializer implements Deserializer<usageMessage>{
    /*private String encoding = "UTF8";
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    @Override
    public usageMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            ByteBuffer buf = ByteBuffer.wrap(data);
            int subscriberID = buf.getInt();
            int packageID = buf.getInt();
            double usedAmount = buf.getDouble();
            int sizeUsedService = buf.getInt();
            byte[] usedServicesBytes = new byte[sizeUsedService];
            buf.get(usedServicesBytes);
            String deserializedUsedService = new String(usedServicesBytes, encoding);
            int sizeMsisdn= buf.getInt();
            byte[]msisdn=new byte[sizeMsisdn];
            buf.get(msisdn);
            String deserializedMsisdn = new String(msisdn);
            usageMessage msg = new usageMessage();
            msg.setSubscriberID(subscriberID);
            msg.setPackageID(packageID);
            msg.setUsedAmount(usedAmount);
            msg.setUsedService(deserializedUsedService);
            msg.setMsisdn(deserializedMsisdn);
            return msg;
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Message");
        }
    }

    @Override
    public void close() {
    }*/
    private ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LogManager.getLogger();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public usageMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                logger.info("Null received at deserializing");
                return null;
            }
            logger.info("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), usageMessage.class);
        } catch (Exception e) {
            logger.info(e);
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {
    }
}

