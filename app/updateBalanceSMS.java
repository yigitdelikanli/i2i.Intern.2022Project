package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class updateBalanceSMS extends VoltProcedure {

    public final SQLStmt SMSUpdate = new SQLStmt("UPDATE BALANCE \n" +
            "    SET BAL_LVL_SMS = BAL_LVL_SMS+? \n" +
            "    WHERE SUBSC_ID = ?;");

    public VoltTable[] run(int SUBSC_ID,long BAL_LVL_SMS,String packageName )
            throws VoltAbortException {
        voltQueueSQL(SMSUpdate, SUBSC_ID,BAL_LVL_SMS);
        return voltExecuteSQL();
    }
}


