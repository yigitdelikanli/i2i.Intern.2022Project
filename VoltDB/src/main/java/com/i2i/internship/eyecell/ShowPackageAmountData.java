package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class ShowPackageAmountData extends VoltProcedure {

    public final SQLStmt showDataAmount = new SQLStmt(
            "SELECT (SELECT AMOUNT_DATA  FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_DATA  \n" +
                    " FROM BALANCE WHERE SUBSC_ID =?;");

    public VoltTable[] run(int  SUBSC_ID)
            throws VoltAbortException {
        voltQueueSQL(showDataAmount, SUBSC_ID);
        return voltExecuteSQL();
    }
}


