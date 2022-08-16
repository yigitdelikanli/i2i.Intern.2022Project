package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class Login extends VoltProcedure {

    public final SQLStmt login = new SQLStmt(
            " SELECT * FROM SUBSCRIBER WHERE MSISDN = ? AND PASSWORD = ?;");

    public VoltTable[] run( String MSISDN,String PASSWORD)
            throws VoltAbortException {
        voltQueueSQL(login,  MSISDN, PASSWORD);
        return voltExecuteSQL();
    }
}
