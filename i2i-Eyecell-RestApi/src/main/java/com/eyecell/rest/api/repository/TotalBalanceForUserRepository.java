package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.TotalBalanceForUser;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;

import java.io.IOException;

public class TotalBalanceForUserRepository {

    public TotalBalanceForUser totalBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        TotalBalanceForUser totalBalanceForUser = new TotalBalanceForUser();
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response ;
        response =client.callProcedure("getPackage",MSISDN);
        VoltTable voltTable = response.getResults()[0];
        voltTable.advanceRow();
        totalBalanceForUser.setVoice(voltTable.getLong(2));
        totalBalanceForUser.setData(voltTable.getLong(3));
        totalBalanceForUser.setSms(voltTable.getLong(4));

        return totalBalanceForUser;

    }
}
