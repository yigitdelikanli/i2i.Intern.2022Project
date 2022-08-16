package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.encryption.Encryption;
import com.eyecell.rest.api.resource.NewSubscriber;
import com.eyecell.rest.api.resource.Subscriber;
import org.com.i2i.intern.EyeCell.HazelcastConfiguration;
import org.voltdb.client.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberRepository {

    OracleDbHelper oracleDbHelper = new OracleDbHelper();
    Encryption encryption = new Encryption();

    public List<Subscriber> getSubscribers() throws SQLException {
        Connection connection = oracleDbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from SUBSCRIBER");
        List<Subscriber> subscriberList = new ArrayList<Subscriber>();
        while (resultSet.next()) {
            subscriberList.add(new Subscriber(
                    resultSet.getLong("SUBSC_ID"),
                    resultSet.getString("MSISDN"),
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

    public void addSubscriberOracleDb(NewSubscriber newSubscriber) throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "{call package_subscriber.create_subscriber(?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        String encryptedPassword = encryption.encrypt(newSubscriber.getPassword());

        callableStatement.setString(1, newSubscriber.getMSISDN());
        callableStatement.setString(2, newSubscriber.getName());
        callableStatement.setString(3, newSubscriber.getSurname());
        callableStatement.setString(4, newSubscriber.getEmail());
        callableStatement.setString(5, encryptedPassword);
        callableStatement.setInt(6, newSubscriber.getPackageId());

        callableStatement.execute();
        connection.close();
    }

    public void addSubscriberVoltDb(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        HazelcastConfiguration hazelcastConfiguration = new HazelcastConfiguration();
        hazelcastConfiguration.initConnection("34.77.94.205","34.77.94.205:5702");

        long subscriberId = hazelcastConfiguration.getMapSize()+1;

        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        String encryptedPassword = encryption.encrypt(newSubscriber.getPassword());
        client.callProcedure(
                "UserInsert",
                subscriberId,
                newSubscriber.getMSISDN(),
                newSubscriber.getName(),
                newSubscriber.getSurname(),
                newSubscriber.getEmail(),
                encryptedPassword,
                newSubscriber.getPackageId());

    }
}
