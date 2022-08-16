package org.example;
import com.method.ProducerMethod;
import com.modul.usageMessage;

import java.util.concurrent.ExecutionException;

public class ProducerDeneme {
    public static void main(String[] args) {
        usageMessage msg = new usageMessage();
        msg.setUsedService("Voice");
        msg.setUsedAmount(5);
        msg.setMsisdn("5350857286");
        try {
            ProducerMethod.runProducer(msg);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
