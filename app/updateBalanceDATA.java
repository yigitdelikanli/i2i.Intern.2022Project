package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class updateBalanceDATA extends VoltProcedure {

    public final SQLStmt DATAUpdate = new SQLStmt("UPDATE BALANCE \n" +
            "    SET BAL_LVL_DATA = BAL_LVL_DATA+? \n" +
            "    WHERE SUBSC_ID = ?;");

    public VoltTable[] run(int SUBSC_ID, long BAL_LVL_DATA,String packageName)
            throws VoltAbortException {
        voltQueueSQL(DATAUpdate, SUBSC_ID,BAL_LVL_DATA);
        return voltExecuteSQL();
    }
}


