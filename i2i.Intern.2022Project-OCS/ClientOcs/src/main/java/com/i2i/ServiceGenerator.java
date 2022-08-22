package com.i2i;

import java.util.Random;

public class ServiceGenerator {
    enum Service{
        VOICE,
        SMS,
        DATA
    }
    Random rand;

    ServiceGenerator(){
        rand = new Random();
    }

    public String getService() {
        Service serv;
        int randNum = rand.nextInt(Service.values().length);
        switch (randNum){
            case 0:
                serv = Service.VOICE;
                break;
            case 1:
                serv = Service.SMS;
                break;
            case 2:
                serv = Service.DATA;
                break;
            default:
                serv = Service.VOICE;
        }
        return serv.toString();
    }
}
