package com.i2i.internship.eyeCell.kafka.modul;

import com.i2i.internship.eyeCell.kafka.constants.kafkaConstants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;

import java.io.Serializable;
import java.util.Properties;

public class UsageMessageProducer {
    public static Producer<Long, Serializable> createProducer(){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConstants.bootstrapServers);
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, kafkaConstants.clientID);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,LongSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"com.i2i.internship.eyeCell.kafka.modul.UsageMessageSerializer");
        properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "30000");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");

        return new KafkaProducer<>(properties);
    }
}
