package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class ShowPackageAmountData extends VoltProcedure {

    public final SQLStmt showDataAmount = new SQLStmt(
            "SELECT (SELECT AMOUNT_DATA  FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_DATA  \n" +
                    " FROM BALANCE WHERE SUBSC_ID =(SELECT SUBSC_ID FROM  SUBSCRIBER WHERE MSISDN =?);");

    public VoltTable[] run(String MSISDN)
            throws VoltAbortException {
        voltQueueSQL(showDataAmount, MSISDN);
        return voltExecuteSQL();
    }
}


