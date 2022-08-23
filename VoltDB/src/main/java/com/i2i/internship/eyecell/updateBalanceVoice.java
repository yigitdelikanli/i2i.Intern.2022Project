package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class updateBalanceVoice extends VoltProcedure {

    public final SQLStmt VoiceUpdate = new SQLStmt("UPDATE BALANCE \n" +
            "    SET BAL_LVL_VOICE = BAL_LVL_VOICE+? \n" +
            "    WHERE SUBSC_ID = ?;");

    public final SQLStmt updatePrice = new SQLStmt(
            "UPDATE BALANCE\n" +
                    "    SET PRICE = PRICE + ? \n" +
                    "    WHERE SUBSC_ID= ?;");


    public VoltTable[] run(int SUBSC_ID, long BAL_LVL_VOICE,String packageName,int PRICE)
            throws VoltAbortException {
        voltQueueSQL(VoiceUpdate, BAL_LVL_VOICE,SUBSC_ID);
        voltQueueSQL(updatePrice, PRICE,SUBSC_ID);
        return voltExecuteSQL();
    }
}
