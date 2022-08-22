package com.i2i;

import java.util.Random;

//TODO change probabilities properly
public class LocationGenerator {
    enum Location{
        TURKEY,
        EUROPE,
        RUSSIA,
        NORTHAMERICA,
        MIDDLEEAST,
        NORTHERNCYPRUS,
        OTHER
    }
    Random rand;

    LocationGenerator(){
        rand = new Random();
    }
    public String getLocation(){
        Location locate;
        int randNum = rand.nextInt(Location.values().length + 10);
        // + 10 is for increasing turkey possibility
        switch (randNum){
            case 0:
                locate = Location.TURKEY;
                break;
            case 1:
                locate = Location.EUROPE;
                break;
            case 2:
                locate = Location.RUSSIA;
                break;
            case 3:
                locate = Location.NORTHAMERICA;
                break;
            case 4:
                locate = Location.MIDDLEEAST;
                break;
            case 5:
                locate = Location.NORTHERNCYPRUS;
                break;
            case 6:
                locate = Location.OTHER;
                break;
            default:
                locate = Location.TURKEY;
        }
        return locate.toString();
    }
}
