package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.resource.Login;
import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;
import java.sql.*;

public class LoginRepository {


    /** OracleDB Login Check **/
    public Integer loginCheck(Login login) throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection connection = dbHelper.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{? = call package_subscriber.login(?,?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        long telNo = Long.parseLong(login.getTelNo());
        callableStatement.setLong(2,telNo);
        callableStatement.setString(3,login.getPassword());
        callableStatement.execute();
        int ret = callableStatement.getInt(1);


        if (ret ==1 ){
            return 1;
        }
        else {
            return 0;
        }
    }


    /**  VOLTDB Login Check **/
    public Integer loginCheck1 (Login login) throws SQLException, IOException, ProcCallException {

            VoltDbHelper voltDbHelper = new VoltDbHelper();
            Client client = voltDbHelper.client();
            ClientResponse response ;
            response =client.callProcedure("Login",login.getTelNo(),login.getPassword());
            VoltTable table =response.getResults()[0];
            if(table.getRowCount()>0){

                return 1;
            }
            else
                return 0;

    }
}
