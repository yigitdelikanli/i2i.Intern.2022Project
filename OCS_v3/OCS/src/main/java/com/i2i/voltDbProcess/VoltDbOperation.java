package com.i2i.voltDbProcess;

import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;

public class VoltDbOperation {

    private String id;
    private int port;
    private ClientResponse response = null;
    private Client client;

    public VoltDbOperation(){    //String id, int port
        this.id="34.159.58.171";
        this.port=49153;
    }

    public Client getConnectionVoltDB() throws IOException, ProcCallException {

        ClientConfig config = new ClientConfig ( "advent",  "xYZZy");
        Client client = ClientFactory.createClient(config);
        client.createConnection ( id,port);

        return  client;
    }

    public String getPackageName(String MSISDN){

        try {
            client =getConnectionVoltDB();
            response = client.callProcedure("getPackage",MSISDN); //"5061176561"
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }
        VoltTable table2 =response.getResults()[0];
        table2.advanceRow();

        return table2.getString(1);
    }

    public long getUidByMSISDN(String MSISDN){

        try {
            client = getConnectionVoltDB();
            response = client.callProcedure("getMSISDNWithId",MSISDN);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }


        VoltTable table3 =response.getResults()[0];
        table3.advanceRow();

        return table3.getLong(0);
    }

    public void sendVoiceAmount(String MSISDN, int usedAmount){

        try {
            int uid= (int) getUidByMSISDN(MSISDN);
            String packageName = getPackageName(MSISDN);

            client = getConnectionVoltDB();
            client.callProcedure("updateBalanceVoice",usedAmount,uid,packageName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

    }

    public void sendSmsAmount(String MSISDN, int usedAmount){

        try {
            int uid= (int) getUidByMSISDN(MSISDN);
            String packageName = getPackageName(MSISDN);

            client = getConnectionVoltDB();
            client.callProcedure("updateBalanceSMS",usedAmount,uid,packageName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

    }

    public void sendGbAmount(String MSISDN, int usedAmount){

        try {
            int uid= (int) getUidByMSISDN(MSISDN);
            String packageName = getPackageName(MSISDN);

            client = getConnectionVoltDB();
            client.callProcedure("updateBalanceData",usedAmount,uid,packageName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }
    }

}
