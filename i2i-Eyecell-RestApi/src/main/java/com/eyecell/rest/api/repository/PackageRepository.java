package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.Package;
import com.eyecell.rest.api.resource.PackageList;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageRepository {
    public List<Package> packageList() throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from PACKAGE");
        List<Package> packageList = new ArrayList<Package>();
        while (resultSet.next()) {
            packageList.add(new Package(resultSet.getLong("PACKAGE_ID"),
                    resultSet.getString("PACKAGE_NAME"),
                    resultSet.getLong("AMOUNT_VOICE"),
                    resultSet.getLong("AMOUNT_DATA"),
                    resultSet.getLong("AMOUNT_SMS"),
                    resultSet.getLong("DURATION")));
        }
        connection.close();
        return packageList;
    }

    public List<PackageList> packageLists() throws SQLException {

        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from PACKAGE");
        List<PackageList> packageLists = new ArrayList<PackageList>();
        while (resultSet.next()) {
            packageLists.add(new PackageList(resultSet.getLong("PACKAGE_ID"),
                    resultSet.getString("PACKAGE_NAME")));
        }
        connection.close();
        return packageLists;

    }

    public List<Package> getPackageByMSISDNinList(String MSISDN) throws IOException, ProcCallException {
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;

        List<Package> packageInfo = new ArrayList<>();

        response = client.callProcedure("getPackage",MSISDN);
        VoltTable tablePackageInfo = response.getResults()[0];
        tablePackageInfo.advanceRow();
        long packageID =tablePackageInfo.getLong(0);
        String packageName =tablePackageInfo.getString(1);
        long amountVoice =tablePackageInfo.getLong(2);
        long amountData =tablePackageInfo.getLong(3);
        long amountSMS =tablePackageInfo.getLong(4);
        long duration =tablePackageInfo.getLong(5);

        packageInfo.add(new Package(packageID,packageName,amountVoice,amountData,amountSMS,duration));
        return packageInfo;

    }

    public Package getPackageByMSISDNinObject(String MSISDN) throws IOException, ProcCallException {
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;


        response = client.callProcedure("getPackage",MSISDN);
        VoltTable tablePackageInfo = response.getResults()[0];
        tablePackageInfo.advanceRow();
        long packageID =tablePackageInfo.getLong(0);
        String packageName =tablePackageInfo.getString(1);
        long amountVoice =tablePackageInfo.getLong(2);
        long amountData =tablePackageInfo.getLong(3);
        long amountSMS =tablePackageInfo.getLong(4);
        long duration =tablePackageInfo.getLong(5);


        return (new Package(packageID,packageName,amountVoice,amountData,amountSMS,duration));

    }


    /**
     *                                   ------Insert Package------ Depreciated
     *
     public String aPackage (String packageName, long amountVoice,long amountInternet,long amountSMS,long duration) throws SQLException {
     DbHelper dbHelper = new DbHelper(username,password,DBurl);
     Connection connection = dbHelper.getConnection();
     String sql = "{call package_package.insertPackage(?,?,?,?,?)}";
     CallableStatement callableStatement = connection.prepareCall(sql);

     callableStatement.setString(1,packageName);
     callableStatement.setLong(2,amountVoice);
     callableStatement.setLong(3,amountInternet);
     callableStatement.setLong(4,amountSMS);
     callableStatement.setLong(5,duration);

     callableStatement.execute();
     connection.close();
     return packageName +" "+ amountVoice +" "+amountInternet+" "+amountSMS+" "+ duration + " : added";

     }
     **/


}
