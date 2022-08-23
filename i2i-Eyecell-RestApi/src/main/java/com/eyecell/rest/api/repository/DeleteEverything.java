/*
package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import org.com.i2i.internship.EyeCell.Hazelcast.HazelcastConfiguration;

import java.sql.*;

public class DeleteEverything {
        PreparedStatement preparedStatement ;

    public void deleteVoltDb() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:voltdb://34.159.58.171:49153");
        String sql = "DELETE FROM SUBSCRIBERS";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    public void deleteOracleDb() throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "TRUNCATE TABLE SUBSCRIBER";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    public void deleteMap(){
        HazelcastConfiguration hazelcastConfiguration = new HazelcastConfiguration();
        hazelcastConfiguration.clearMapValues();
        hazelcastConfiguration.clearMapValues();
        hazelcastConfiguration.clearMapValues();
    }
}
*/
