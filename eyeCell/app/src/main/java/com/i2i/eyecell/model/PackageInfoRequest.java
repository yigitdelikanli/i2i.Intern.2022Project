package com.i2i.eyecell.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageInfoRequest {
    public PackageInfoRequest(int amountData, int amountSms, int amountVoice,int packageId,String packageName,int duration) {
        this.amountData = amountData;
        this.amountSms = amountSms;
        this.amountVoice = amountVoice;
        this.packageId = packageId;
        this.packageName = packageName;
        this.duration = duration;
    }
    @SerializedName("amountData")
    @Expose
    int amountData;
    @SerializedName("amountSms")
    @Expose
    int amountSms;
    @SerializedName("amountVoice")
    @Expose
    int amountVoice;
    @SerializedName("packageID")
    @Expose
    int packageId;
    @SerializedName("packageName")
    @Expose
    String packageName;

    @SerializedName("duration")
    @Expose
    int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getAmountVoice() {
        return amountVoice;
    }

    public void setAmountVoice(int amountVoice) {
        this.amountVoice = amountVoice;
    }

    public int getAmountSms() {
        return amountSms;
    }

    public void setAmountSms(int amountSms) {
        this.amountSms = amountSms;
    }

    public int getAmountData() {
        return amountData;
    }

    public void setAmountData(int amountData) {
        this.amountData = amountData;
    }
}
