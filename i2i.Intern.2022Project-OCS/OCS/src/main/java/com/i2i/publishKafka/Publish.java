package com.i2i.publishKafka;

import java.util.ArrayList;
import java.util.List;

public class Publish {
    private List<Subscriber> subscribers =new ArrayList<>();

    public void createSubsriber(){

        for (int i=0;i<20;i++){
            subscribers.add(new Subscriber(i,"ali","veli"));
        }

    }

    public void sendSubscribers(){
            //
    }

}
