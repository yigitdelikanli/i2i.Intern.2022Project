package com.i2isystem.app;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class updateBalanceVoice extends VoltProcedure {

    public final SQLStmt VoiceUpdate = new SQLStmt("UPDATE BALANCE \n" +
            "    SET BAL_LVL_VOICE = BAL_LVL_VOICE+? \n" +
            "    WHERE SUBSC_ID = ?;");

    public VoltTable[] run(int SUBSC_ID, long BAL_LVL_VOICE,String packageName)
            throws VoltAbortException {
        voltQueueSQL(VoiceUpdate, SUBSC_ID,BAL_LVL_VOICE);
        return voltExecuteSQL();
    }
}
