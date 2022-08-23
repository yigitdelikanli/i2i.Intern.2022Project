package com.eyecell.rest.api.resource;

public class TotalBalanceForUser {

    private long data;
    private long sms;
    private long voice;

    public TotalBalanceForUser() {
    }

    public TotalBalanceForUser(long data, long sms, long voice) {
        this.data = data;
        this.sms = sms;
        this.voice = voice;
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
}
