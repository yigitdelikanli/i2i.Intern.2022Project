package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class getPackage extends VoltProcedure {

    public final SQLStmt getPackageQuery  = new SQLStmt("SELECT * FROM PACKAGE WHERE PACKAGE_ID = (SELECT  PACKAGE_ID FROM BALANCE WHERE SUBSC_ID =(SELECT SUBSC_ID \n" +
            "FROM SUBSCRIBER WHERE MSISDN =?) );");

    public VoltTable[] run(String MSISDN)
            throws VoltAbortException {
        voltQueueSQL(getPackageQuery, MSISDN);
        return voltExecuteSQL();
    }

}

