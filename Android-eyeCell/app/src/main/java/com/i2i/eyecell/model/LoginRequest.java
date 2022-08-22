package com.i2i.eyecell.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("password")
    @Expose
    private String password;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPassword(String s) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
