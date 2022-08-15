package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.BalanceForUser;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;

import java.io.IOException;

public class BalanceForUserRepository {
    public BalanceForUser getBalanceByMSISDN (String MSISDN) throws IOException, ProcCallException {
        BalanceForUser balanceForUser = new BalanceForUser();
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response ;
        response =client.callProcedure("ShowPackageAmountVoice",MSISDN);
        VoltTable tableVoiceAmount = response.getResults()[0];
        long voice = tableVoiceAmount.fetchRow(0).getLong(0);
        balanceForUser.setVoice(voice);

        response= client.callProcedure("ShowPackageAmountData",MSISDN);
        VoltTable tableDataAmount = response.getResults()[0];
        long data = tableDataAmount.fetchRow(0).getLong(0);
        balanceForUser.setData(data);

        response= client.callProcedure("ShowPackageAmountSMS",MSISDN);
        VoltTable tableSmsAmount = response.getResults()[0];
        long SMS = tableSmsAmount.fetchRow(0).getLong(0);
        balanceForUser.setSms(SMS);
        return balanceForUser;



    }
}
