package com.i2i;

import java.util.Random;

public class AmountGenerator {

    Random rand;
    final int bound = 120;

    AmountGenerator(){
        rand = new Random();
    }
    public int getAmount(){
        return rand.nextInt(bound);
    }
}
