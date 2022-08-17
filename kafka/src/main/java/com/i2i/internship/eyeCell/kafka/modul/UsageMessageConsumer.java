package com.i2i.internship.eyeCell.kafka.modul;

import com.i2i.internship.eyeCell.kafka.constants.kafkaConstants;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class UsageMessageConsumer {
    public static Consumer <Long, UsageMessage> createConsumer(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConstants.bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroup");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, kafkaConstants.clientID);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.i2i.internship.eyeCell.kafka.modul.UsageMessageDeserializer");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConstants.autoOffsetReset);

        Consumer<Long, UsageMessage> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(kafkaConstants.topicName));
        return consumer;
    }
}
