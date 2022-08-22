package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class updateBalanceDATA extends VoltProcedure {

    public final SQLStmt DATAUpdate = new SQLStmt("UPDATE BALANCE \n" +
            "    SET BAL_LVL_DATA = BAL_LVL_DATA+? \n" +
            "    WHERE SUBSC_ID = ?;");

    public final SQLStmt updatePrice = new SQLStmt(
            "UPDATE BALANCE\n" +
                    "    SET PRICE=PRICE +?\n" +
                    "    WHERE SUBSC_ID= ?;");


    public VoltTable[] run( int SUBSC_ID ,int BAL_LVL_DATA ,String packageName,int PRICE )
            throws VoltAbortException {
        voltQueueSQL(DATAUpdate, BAL_LVL_DATA ,SUBSC_ID);
        voltQueueSQL(updatePrice, PRICE,SUBSC_ID);
        return voltExecuteSQL();
    }
}


