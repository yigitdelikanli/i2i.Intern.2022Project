package com.i2i.VoltDBProcess;


import org.voltdb.VoltTable;
import org.voltdb.client.*;
import java.io.IOException;

import org.voltdb.client.Client;

public class VoltDbOperation {

    private String id;
    private int port;

    public VoltDbOperation(String id, int port){
        this.id=id;                             //"34.159.58.171";
        this.port=port;                          //49153;
    }

    public Client getConnectionVoltDB() throws IOException, ProcCallException {

        ClientConfig  config = new ClientConfig ( "advent",  "xYZZy");
        Client client = ClientFactory.createClient(config);
        client.createConnection ( id,port);

        return  client;
    }

    public String getPackageName(String MSISDN){

        ClientResponse  response = null;
        Client client;

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

    public int getUidByMSISDN(String MSISDN){


        return 0;
    }

    public void updateBalance(){

    }

    public void sendVoiceAmount(int uid,int usedAmount,String packageName){



    }

    public void sendSmsAmount(int uid,int usedAmount,String packageName){



    }

    public void sendGbAmount(int uid,int usedAmount,String packageName){



    }


}
