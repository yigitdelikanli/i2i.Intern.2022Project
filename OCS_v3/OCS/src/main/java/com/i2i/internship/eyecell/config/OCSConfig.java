package com.i2i.internship.eyecell.config;

public class OCSConfig {
    private static String chargingFilePath;


    public static String getChargingFilePath() {
        return chargingFilePath;
    }

    public static void setChargingFilePath(String chargingFilePath) {
        OCSConfig.chargingFilePath = chargingFilePath;
    }
}

