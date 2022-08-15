package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;
import java.sql.*;

public class LoginRepository {

    private String username = "eyecell";
    private String password = "12345";
    private String DBurl = "jdbc:oracle:thin:@34.77.240.18:49161:xe";
    public Integer loginCheck(long telNo, String password) throws SQLException {
        DbHelper dbHelper = new DbHelper(username,this.password,DBurl);
        Connection connection = dbHelper.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{? = call package_subscriber.login(?,?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.setLong(2,telNo);
        callableStatement.setString(3,password);
        callableStatement.execute();
        int ret = callableStatement.getInt(1);


        if (ret ==1 ){
            return 1;
        }
        else {
            return 0;
        }
    }

    public Integer loginCheck1 (String telNo, String password) throws SQLException, IOException, ProcCallException {

            VoltDbHelper voltDbHelper = new VoltDbHelper();
            Client client = voltDbHelper.client();
            ClientResponse response ;
            response =client.callProcedure("Login",telNo,password);
            VoltTable table =response.getResults()[0];
            if(table.getRowCount()>0){

                //Sisteme girer
                return 1;
            }
            else
                return 0;

//Bununlan da yeni kullanıcı oluşturursun
           /* client.callProcedure("UserInsert",6900,53656 ,"123","as","FFF",13,1123);*/


    }
}
