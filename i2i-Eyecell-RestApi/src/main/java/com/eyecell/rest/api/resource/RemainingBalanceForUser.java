package com.eyecell.rest.api.resource;

public class RemainingBalanceForUser {
    private long data;
    private long sms;
    private long voice;

    private long price;


    public RemainingBalanceForUser(long data, long sms, long voice, long price) {
        this.data = data;
        this.sms = sms;
        this.voice = voice;
        this.price = price;
    }

    public RemainingBalanceForUser() {
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public long getSms() {
        return sms;
    }

    public void setSms(long sms) {
        this.sms = sms;
    }

    public long getVoice() {
        return voice;
    }

    public void setVoice(long voice) {
        this.voice = voice;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
