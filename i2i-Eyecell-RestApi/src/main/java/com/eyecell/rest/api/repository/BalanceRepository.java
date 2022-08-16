package com.eyecell.rest.api.repository;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.resource.Balance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BalanceRepository {
    DbHelper dbHelper = new DbHelper();

    public List<Balance> getBalances() throws SQLException {
        Connection connection = dbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from BALANCE");
        List<Balance> balances = new ArrayList<Balance>();
        while (resultSet.next()) {
            balances.add(new Balance(
                    resultSet.getLong("SUBSC_ID"),
                    resultSet.getLong("PACKAGE_ID"),
                    resultSet.getLong("BAL_LVL_VOICE"),
                    resultSet.getLong("BAL_LVL_SMS"),
                    resultSet.getLong("BAL_LVL_DATA"),
                    resultSet.getDate("SDATE"),
                    resultSet.getDate("EDATE")));

        }
        connection.close();
        return balances;

    }
}

