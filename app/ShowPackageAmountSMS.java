package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class ShowPackageAmountSMS extends VoltProcedure {

    public final SQLStmt showSMSAmount = new SQLStmt(
            "SELECT (SELECT AMOUNT_SMS FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_SMS \n" +
                    " FROM BALANCE WHERE SUBSC_ID =(SELECT SUBSC_ID FROM  SUBSCRIBER WHERE MSISDN =?);");

    public VoltTable[] run(String MSISDN)
            throws VoltAbortException {
        voltQueueSQL(showSMSAmount, MSISDN);
        return voltExecuteSQL();
    }

}

