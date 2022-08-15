package com.eyecell.rest.api.resource;

import java.util.Date;

public class Subscriber {
    private final long subscriberId;
    private final long msisdn;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Date sDate;
    private final String status;

    public Subscriber(long subscriberId, long msisdn, String name, String surname, String email, String password, Date sDate, String status) {
        this.subscriberId = subscriberId;
        this.msisdn = msisdn;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sDate = sDate;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public long getMsisdn() {
        return msisdn;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {return email;}

    public Date getsDate() {
        return sDate;
    }

    public String getStatus() {
        return status;
    }
}
