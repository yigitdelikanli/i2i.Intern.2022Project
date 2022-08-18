package com.i2i.internship.eyeCell.UpdateDatabase;

import com.i2i.internship.eyeCell.UpdateDatabase.method.SF_UpdateOracledbMethod;

import java.sql.SQLException;

public class SF_UpdateOracledb {
    public static void main(String[] args) {
        try {
            SF_UpdateOracledbMethod.runConsumer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
