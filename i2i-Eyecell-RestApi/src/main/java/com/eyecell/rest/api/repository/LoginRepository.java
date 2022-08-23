package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.encryption.Encryption;
import com.eyecell.rest.api.resource.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;

public class LoginRepository {
    Encryption encryption = new Encryption();
    /**
     * OracleDB Login Check
     **/
    public ResponseEntity loginCheck(@PathVariable String MSISDN, @PathVariable String password) throws SQLException {
        Login[] logins = {new Login(MSISDN, password)};
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String encryptedPassword = encryption.encrypt(password);

        CallableStatement callableStatement = connection.prepareCall("{ ? = call package_subscriber.login(?,?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.setString(2, MSISDN);
        callableStatement.setString(3, encryptedPassword);
        callableStatement.execute();

        int checkUser = callableStatement.getInt(1);
        if (checkUser == 1) {
            return new ResponseEntity<>(logins, HttpStatus.OK);

        } else
            return new ResponseEntity<>("User not Found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity loginCheckAndroid(@PathVariable String MSISDN, @PathVariable String password) throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String encryptedPassword = encryption.encrypt(password);

        CallableStatement callableStatement = connection.prepareCall("{? = call package_subscriber.login(?,?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.setString(2, MSISDN);
        callableStatement.setString(3, encryptedPassword);
        callableStatement.execute();

        int checkUser = callableStatement.getInt(1);
        if (checkUser == 1) {
            return new ResponseEntity<>(new Login(MSISDN, password), HttpStatus.OK);

        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    /**
     *
     *                              ---      VOLTDB Login Check    ---- Depreciated
     *
     *
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
     **/
}
