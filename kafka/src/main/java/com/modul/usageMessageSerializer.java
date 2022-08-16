package com.modul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

//@Slf4j
public class usageMessageSerializer implements Serializer<usageMessage> {
    /*
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, usageMessage data) {
        //string msisdn
        //string usedService
        int sizeMsisdn;
        byte[] serializedMsisdn;
        int sizeUsedService;
        byte[] serializedUsedService;
        try {
            if (data == null) {
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            serializedMsisdn = data.getMsisdn().getBytes(encoding);
            sizeMsisdn = serializedMsisdn.length;
            serializedUsedService=data.getUsedService().getBytes(encoding);
            sizeUsedService=serializedUsedService.length;
            ByteBuffer buf = ByteBuffer.allocate(4 + 4 + 8+ sizeUsedService + sizeMsisdn);
            buf.putInt(data.getSubscriberID());
            buf.putInt(data.getPackageID());
            buf.putDouble(data.getUsedAmount());
            buf.putInt(sizeUsedService);
            buf.put(serializedUsedService);
            buf.putInt(sizeMsisdn);
            buf.put(serializedMsisdn);
            return buf.array();

        } catch (Exception e) {
            throw new SerializationException("Error when serializing Message to to byte[]");
        }
    }

    @Override
    public void close() {
    }*/
    Logger logger = LogManager.getLogger();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, usageMessage data) {
        try {
            if (data == null){
                logger.info("Null received at serializing");
                return null;
            }
            logger.info("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            logger.info(e);
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }

    @Override
    public void close() {
    }
}
