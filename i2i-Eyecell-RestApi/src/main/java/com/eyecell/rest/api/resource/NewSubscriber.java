package com.eyecell.rest.api.resource;

public class NewSubscriber {
    private String MSISDN;
    private int packageId;
    private String name;
    private String surname;
    private String password;
    private String email;

    public NewSubscriber(String MSISDN, int packageId, String name, String surname, String password, String email) {
        this.MSISDN = MSISDN;
        this.packageId = packageId;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
