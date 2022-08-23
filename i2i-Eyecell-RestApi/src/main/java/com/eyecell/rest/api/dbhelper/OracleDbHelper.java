package com.eyecell.rest.api.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class OracleDbHelper {
    private final String username = "eyecell";
    private final String password = "12345";
    private final String dBurl = "jdbc:oracle:thin:@34.77.240.18:49161:xe";

    public OracleDbHelper() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        TimeZone.setDefault(timeZone);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dBurl, username, password);
    }
}