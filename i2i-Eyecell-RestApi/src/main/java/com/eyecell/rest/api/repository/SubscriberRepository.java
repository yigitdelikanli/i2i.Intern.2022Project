package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.Subscriber;
import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberRepository {
    private int idCounter = 4;

    private String username = "eyecell";
    private String password = "12345";
    private String DBurl = "jdbc:oracle:thin:@34.77.240.18:49161:xe";

    DbHelper dbHelper = new DbHelper(username, password, DBurl);


    public List<Subscriber> getSubscribers() throws SQLException {
        Connection connection = dbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from SUBSCRIBER");
        List<Subscriber> subscriberList = new ArrayList<Subscriber>();
        while (resultSet.next()) {
            subscriberList.add(new Subscriber(
                    resultSet.getLong("SUBSC_ID"),
                    resultSet.getLong("MSISDN"),
                    resultSet.getString("NAME"),
                    resultSet.getString("SURNAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getDate("SDATE"),
                    resultSet.getString("STATUS")));
        }
        connection.close();
        return subscriberList;

    }

    public String addSubscriberOracleDb(long telNo, String name, String surname, String email, String password) throws SQLException {
        DbHelper dbHelper = new DbHelper(username, this.password, DBurl);
        Connection connection = dbHelper.getConnection();
        String sql = "{call package_subscriber.create_subscriber(?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);

        callableStatement.setLong(1, telNo);
        callableStatement.setString(2, name);
        callableStatement.setString(3, surname);
        callableStatement.setString(4, email);
        callableStatement.setString(5, password);
        callableStatement.setString(6, "Eyecell 10GB");

        callableStatement.execute();
        connection.close();
        return "added";
    }

    public String addSubscriberVoltDb(String MSISDN, String name, String surname, String email, String password, int packageId) throws SQLException, IOException, ProcCallException {

        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        client.callProcedure("UserInsert",idCounter,MSISDN,name,surname,email,password,packageId);
        idCounter++;

        return "User added";
    }


}
