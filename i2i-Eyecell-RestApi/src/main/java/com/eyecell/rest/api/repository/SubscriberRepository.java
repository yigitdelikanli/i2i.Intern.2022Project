package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.encryption.Encryption;
import com.eyecell.rest.api.resource.NewSubscriber;
import com.eyecell.rest.api.resource.Subscriber;
import org.com.i2i.internship.EyeCell.Hazelcast.HazelcastConfiguration;
import org.voltdb.client.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberRepository {
    HazelcastConfiguration hazelcastConfiguration;
    OracleDbHelper oracleDbHelper = new OracleDbHelper();
    Encryption encryption = new Encryption();
    private Long tempUI ;
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
                    resultSet.getString("STATUS"),
                    resultSet.getString("SECURITY_QUESTION")));
        }
        connection.close();
        return subscriberList;

    }

    public void addSubscriberOracleDb(NewSubscriber newSubscriber) throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "{call package_subscriber.create_subscriber(?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        String encryptedPassword = encryption.encrypt(newSubscriber.getPassword());

        callableStatement.setLong(1,tempUI);
        callableStatement.setString(2, newSubscriber.getMSISDN());
        callableStatement.setString(3, newSubscriber.getName());
        callableStatement.setString(4, newSubscriber.getSurname());
        callableStatement.setString(5, newSubscriber.getEmail());
        callableStatement.setString(6, encryptedPassword);
        callableStatement.setString(7,newSubscriber.getSecurityQuestion());
        callableStatement.setInt(8, newSubscriber.getPackageId());

        callableStatement.execute();
        connection.close();
    }

    public SubscriberRepository() {
        hazelcastConfiguration = new HazelcastConfiguration();
        hazelcastConfiguration.initConnection("34.77.94.205", "34.77.94.205", "Customers Map (Current Map)");
    }

    public void addSubscriberVoltDb(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        String encryptedPassword = encryption.encrypt(newSubscriber.getPassword());
        client.callProcedure(
                "UserInsert",
                (getUI()),
                newSubscriber.getMSISDN(),
                newSubscriber.getName(),
                newSubscriber.getSurname(),
                newSubscriber.getEmail(),
                encryptedPassword,
                newSubscriber.getSecurityQuestion(),
                newSubscriber.getPackageId());
        hazelcastConfiguration.putMsisdn(newSubscriber.getMSISDN(), tempUI);
    }

    public Long getUI() throws SQLException {
        OracleDbHelper dbHelper = new OracleDbHelper();
        Connection connection = dbHelper.getConnection();
        String sql = "{ ? = call package_subscriber.get_subscriber_Id}";

        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        Long UI = callableStatement.getLong(1);

        tempUI = UI;
        connection.close();
        return UI;
    }
}
