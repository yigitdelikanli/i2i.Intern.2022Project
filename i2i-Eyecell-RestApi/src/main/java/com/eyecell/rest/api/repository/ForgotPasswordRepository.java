package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.OracleDbHelper;
import com.eyecell.rest.api.encryption.Encryption;
import com.eyecell.rest.api.mail.MailSender;
import com.eyecell.rest.api.resource.ForgotPassword;
import com.eyecell.rest.api.resource.Login;
import org.hsqldb_voltpatches.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;



public class ForgotPasswordRepository {

    public ResponseEntity forgotPassword(ForgotPassword forgotPassword) throws SQLException {
        MailSender mailSender = new MailSender();
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "{? = call package_subscriber.forget_password(?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);

        callableStatement.registerOutParameter(1, Types.VARCHAR);

        callableStatement.setString(2, forgotPassword.getEmail());
        callableStatement.setString(3, forgotPassword.getSecurityQuestion());
        callableStatement.execute();

        String password = callableStatement.getString(1);

        if (password != null) {

            Encryption encryption = new Encryption();

            String password1 = encryption.Decryption(password);
            String toEmail = forgotPassword.getEmail();
            String subject = "Eyecell Password";
            String body = "Your password is " + password1;

            mailSender.sendEmail(toEmail, subject, body);

            return new ResponseEntity<>("E-mail is sent", HttpStatus.OK);

        } else
            return new ResponseEntity<>("User not Found", HttpStatus.BAD_REQUEST);
    }
}


