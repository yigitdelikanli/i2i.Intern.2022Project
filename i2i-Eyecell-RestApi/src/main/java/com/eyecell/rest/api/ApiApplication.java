package com.eyecell.rest.api;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import org.com.i2i.internship.EyeCell.Hazelcast.HazelcastConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) throws SQLException {

        /*OracleDbHelper dbHelper = new OracleDbHelper();
        Connection connection = dbHelper.getConnection();
        String sql = "{ ? = call package_subscriber.get_subscriber_Id}";

        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.NUMERIC);
        callableStatement.execute();
        int ret = callableStatement.getInt(1);
        System.out.println(ret);*/
       SpringApplication.run(ApiApplication.class, args);

    }
}
