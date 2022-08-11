package com.i2i;

import java.util.Random;

public class LocationGenerator {
    enum Location{
        Turkey,
        Abroad
    }
    Random rand;

    LocationGenerator(){
        rand = new Random();
    }
    public String getLocation(){
        Location locate;
        int randNum = rand.nextInt(Location.values().length);
        switch (randNum){
            case 0:
                locate = Location.Turkey;
                break;
            case 1:
                locate = Location.Abroad;
                break;
            default:
                locate = Location.Turkey;
        }
        return locate.toString();
    }
}
