package com.i2i;

import java.io.Serializable;

public class Message {
    public static class Sum implements Serializable {
        private int first;
        private int second;

        public Sum(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }

    public static class Usage implements Serializable{
        private String msisdn;
        private String opNumber;
        private String location;
        private String service;
        private int amount;

        public Usage (String msisdn, String opNumber, String location, String service, int amount){
            this.msisdn = msisdn;
            this.opNumber = opNumber;
            this.location = location;
            this.service = service;
            this.amount = amount;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public String getOpNumber() {return opNumber;}

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

    public static class Result implements Serializable {
        private int result;

        public Result(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }
}