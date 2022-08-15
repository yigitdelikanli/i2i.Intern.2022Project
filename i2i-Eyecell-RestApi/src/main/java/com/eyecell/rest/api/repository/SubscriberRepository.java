package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.NewSubscriber;
import com.eyecell.rest.api.resource.Subscriber;
import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.com.i2i.intern.EyeCell.HazelcastConfiguration;

public class SubscriberRepository {
    private int idCounter = 1;
    DbHelper dbHelper = new DbHelper();

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

    public String addSubscriberOracleDb(NewSubscriber newSubscriber) throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection connection = dbHelper.getConnection();
        String sql = "{call package_subscriber.create_subscriber(?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);

        callableStatement.setLong(1, newSubscriber.getTelNo());
        callableStatement.setInt(2, newSubscriber.getPackageId());
        callableStatement.setString(3, newSubscriber.getName());
        callableStatement.setString(4, newSubscriber.getSurname());
        callableStatement.setString(5, newSubscriber.getPassword());
        callableStatement.setString(6, newSubscriber.getEmail());

        callableStatement.execute();
        connection.close();
        return "user added";
    }

    public String addSubscriberVoltDb(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {

        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        client.callProcedure("UserInsert",
                idCounter,
                newSubscriber.getTelNo(),
                newSubscriber.getName(),
                newSubscriber.getSurname(),
                newSubscriber.getEmail(),
                newSubscriber.getPassword(),
                newSubscriber.getPackageId());

        idCounter++;

        /*HazelcastConfiguration hazelcastConfiguration = new HazelcastConfiguration();
        hazelcastConfiguration.initConnection("34.77.94.205","34.77.94.205:5702");
        hazelcastConfiguration.putMsisdn(idCounter,MSISDN);*/


        return "User added";


    }


}
