package com.i2i.eyecell.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageBalanceRemaining {

    @SerializedName("data")
    @Expose
    int data;
    @SerializedName("sms")
    @Expose
    int sms;
    @SerializedName("voice")
    @Expose
    int voice;
    @SerializedName("price")
    @Expose
    int price;

    public PackageBalanceRemaining(int data,int voice,int sms,int price) {
        this.data = data;
        this.voice = voice;
        this.sms = sms;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
