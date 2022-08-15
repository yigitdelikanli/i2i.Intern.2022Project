package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.resource.Package;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageRepository {



    public List<Package> packageList () throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection connection = dbHelper.getConnection();
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

    /*public String aPackage (String packageName, long amountVoice,long amountInternet,long amountSMS,long duration) throws SQLException {
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

    }*/


}
