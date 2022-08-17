package com.i2i.internship.eyeCell.exampleKafka;
import com.i2i.internship.eyeCell.kafka.method.ProducerMethod;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;

import java.util.concurrent.ExecutionException;

public class ProducerTest {
    public static void main(String[] args) {
        UsageMessage msg = new UsageMessage();
        msg.setUsedService("Voice");
        msg.setUsedAmount(5);
        msg.setMsisdn("5350857286");
        msg.setPrice(2);
        msg.setSubscriberID(1);
        try {
            ProducerMethod.runProducer(msg);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
