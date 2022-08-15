package com.eyecell.rest.api.resource;

public class NewSubscriber {
    private long telNo;
    private int packageId;
    private String name;
    private String surname;
    private String password;
    private String email;

    public NewSubscriber(long telNo, int packageId, String name, String surname, String password, String email) {
        this.telNo = telNo;
        this.packageId = packageId;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public long getTelNo() {
        return telNo;
    }

    public void setTelNo(long telNo) {
        this.telNo = telNo;
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
