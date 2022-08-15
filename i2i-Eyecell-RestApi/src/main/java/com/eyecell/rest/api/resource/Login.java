package com.eyecell.rest.api.resource;

public class Login {
    private String telNo;
    private String password;

    public Login(String telNo, String password) {
        this.telNo = telNo;
        this.password = password;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
