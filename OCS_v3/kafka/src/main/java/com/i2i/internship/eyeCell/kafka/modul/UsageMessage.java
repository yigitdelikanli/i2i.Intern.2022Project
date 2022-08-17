package com.i2i.internship.eyeCell.kafka.modul;

import java.io.Serializable;

public class UsageMessage implements Serializable {
    private String msisdn;
    private long usedAmount;
    private String usedService;

    @Override
    public String toString() {
        return "MSISDN:" + getMsisdn() +  ", Used Amount: " + getUsedAmount() +", Used Service: "+ getUsedService();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }


    public long getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(long usedAmount) {
        this.usedAmount = usedAmount;
    }

    public String getUsedService() {
        return usedService;
    }

    public void setUsedService(String usedService) {
        this.usedService = usedService;
    }
}
