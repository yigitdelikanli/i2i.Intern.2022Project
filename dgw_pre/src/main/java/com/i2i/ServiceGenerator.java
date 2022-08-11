package com.i2i;

import java.util.Random;

public class ServiceGenerator {
    enum Service{
        PhoneCall,
        SMS,
        CellularData
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
                serv = Service.PhoneCall;
                break;
            case 1:
                serv = Service.SMS;
                break;
            case 2:
                serv = Service.CellularData;
                break;
            default:
                serv = Service.PhoneCall;
        }
        return serv.toString();
    }
}
