package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class ShowPackageAmountSMS extends VoltProcedure {

    public final SQLStmt showSMSAmount = new SQLStmt(
            "SELECT (SELECT AMOUNT_SMS FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_SMS \n" +
                    " FROM BALANCE WHERE SUBSC_ID = ?;");

    public VoltTable[] run(int  SUBSC_ID)
            throws VoltAbortException {
        voltQueueSQL(showSMSAmount, SUBSC_ID);
        return voltExecuteSQL();
    }

}


