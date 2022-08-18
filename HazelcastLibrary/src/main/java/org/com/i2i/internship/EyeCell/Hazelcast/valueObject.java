package org.com.i2i.internship.EyeCell.Hazelcast;

import java.io.Serializable;

public class valueObject implements Serializable
{
    String msisdn;
    long Uid;
    public valueObject(long Uid, String msisdn)
    {
        this.Uid = Uid;
        this.msisdn = msisdn;
    }
}
