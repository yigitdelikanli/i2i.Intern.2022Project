package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.TotalBalanceForAllUsers;
import com.eyecell.rest.api.resource.RemainingBalanceForUser;
import com.eyecell.rest.api.resource.TotalBalanceForUser;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BalanceRepository {
    OracleDbHelper oracleDbHelper = new OracleDbHelper();
    public List<TotalBalanceForAllUsers> getBalances() throws SQLException {
        Connection connection = oracleDbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from BALANCE");
        List<TotalBalanceForAllUsers> balances = new ArrayList<TotalBalanceForAllUsers>();
        while (resultSet.next()) {
            balances.add(new TotalBalanceForAllUsers(
                    resultSet.getLong("SUBSC_ID"),
                    resultSet.getLong("PACKAGE_ID"),
                    resultSet.getLong("BAL_LVL_VOICE"),
                    resultSet.getLong("BAL_LVL_SMS"),
                    resultSet.getLong("BAL_LVL_DATA"),
                    resultSet.getDate("SDATE"),
                    resultSet.getDate("EDATE")));
        }
        connection.close();
        return balances;
    }

    public RemainingBalanceForUser getBalanceByMSISDN(String MSISDN) throws IOException, ProcCallException {
        RemainingBalanceForUser balanceForUser = new RemainingBalanceForUser();
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;
        response = client.callProcedure("ShowPackageAmountVoice", MSISDN);
        VoltTable tableVoiceAmount = response.getResults()[0];
        long voice = tableVoiceAmount.fetchRow(0).getLong(0);
        balanceForUser.setVoice(voice);

        response = client.callProcedure("ShowPackageAmountData", MSISDN);
        VoltTable tableDataAmount = response.getResults()[0];
        long data = tableDataAmount.fetchRow(0).getLong(0);
        balanceForUser.setData(data);

        response = client.callProcedure("ShowPackageAmountSMS", MSISDN);
        VoltTable tableSmsAmount = response.getResults()[0];
        long SMS = tableSmsAmount.fetchRow(0).getLong(0);
        balanceForUser.setSms(SMS);
        return balanceForUser;
    }

    public TotalBalanceForUser totalBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        TotalBalanceForUser totalBalanceForUser = new TotalBalanceForUser();
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;
        response = client.callProcedure("getPackage", MSISDN);
        VoltTable voltTable = response.getResults()[0];
        voltTable.advanceRow();
        totalBalanceForUser.setVoice(voltTable.getLong(2));
        totalBalanceForUser.setData(voltTable.getLong(3));
        totalBalanceForUser.setSms(voltTable.getLong(4));

        return totalBalanceForUser;
    }
}

