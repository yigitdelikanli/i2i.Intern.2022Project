package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class ShowPrice extends VoltProcedure {
    public final SQLStmt showPrice = new SQLStmt(
            "SELECT PRICE FROM BALANCE WHERE SUBSC_ID = ?;");
    public VoltTable[] run(int SUBSC_ID)
            throws VoltAbortException {
        voltQueueSQL(showPrice, SUBSC_ID);
        return voltExecuteSQL();
    }
}
