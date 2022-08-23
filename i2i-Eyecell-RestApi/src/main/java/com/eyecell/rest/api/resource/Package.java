package com.eyecell.rest.api.resource;

public class Package {
    private final long packageID;
    private final String packageName;
    private final long amountVoice;
    private final long amountData;
    private final long amountSms;
    private final long duration;

    public Package(long packageID, String packageName, long amountVoice, long amountData, long amountSms, long duration) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.amountVoice = amountVoice;
        this.amountData = amountData;
        this.amountSms = amountSms;
        this.duration = duration;
    }

    public long getPackageID() {
        return packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public long getAmountVoice() {
        return amountVoice;
    }

    public long getAmountData() {
        return amountData;
    }

    public long getAmountSms() {
        return amountSms;
    }

    public long getDuration() {
        return duration;
    }
}
