package com.i2i;

import java.io.Serializable;

public class Message {
    public static class Usage implements Serializable {
        private String msisdn;
        private String location;
        private String service;
        private int amount;

        public Usage(String msisdn, String location, String service, int amount) {
            this.msisdn = msisdn;
            this.location = location;
            this.service = service;
            this.amount = amount;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public String getLocation() {
            return location;
        }

        public String getService() {
            return service;
        }

        public int getAmount() {
            return amount;
        }
    }

}