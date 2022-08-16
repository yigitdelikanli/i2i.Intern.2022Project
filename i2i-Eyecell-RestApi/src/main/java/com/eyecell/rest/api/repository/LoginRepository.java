package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.dbhelper.VoltDbHelper;
import com.eyecell.rest.api.encryption.Encryption;
import com.eyecell.rest.api.resource.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;
import java.sql.*;

public class LoginRepository {
    Encryption encryption = new Encryption();

    /** OracleDB Login Check **/
    public ResponseEntity loginCheck(@PathVariable String MSISDN,@PathVariable String password) throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection connection = dbHelper.getConnection();
        String encryptedPassword = encryption.encrypt(password);

        CallableStatement callableStatement = connection.prepareCall("{? = call package_subscriber.login(?,?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        /*long telNo = Long.parseLong(login.getTelNo());*/
        callableStatement.setString(2,MSISDN);
        callableStatement.setString(3,encryptedPassword);
        callableStatement.execute();
        int ret = callableStatement.getInt(1);

        if (ret ==1 ){
            return new ResponseEntity<>(new Login(MSISDN,password), HttpStatus.OK);

        }
        else
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }


    /**  VOLTDB Login Check **/
    public ResponseEntity<Login> loginCheck1 (Login login) throws SQLException, IOException, ProcCallException {


            VoltDbHelper voltDbHelper = new VoltDbHelper();
            Client client = voltDbHelper.client();
            ClientResponse response ;
            response =client.callProcedure("Login",login.getMSISDN(),login.getPassword());
            VoltTable table =response.getResults()[0];
            if(table.getRowCount()>0){

                return new ResponseEntity<>(new Login(login.getMSISDN(),login.getPassword()), HttpStatus.OK);

            }
            else
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
}
