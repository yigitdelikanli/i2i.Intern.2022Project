package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class ShowUserInformation extends VoltProcedure {
    public final SQLStmt ShowUserInformation = new SQLStmt(
            "SELECT * FROM SUBSCRIBER WHERE MSISDN = ?;");

    public VoltTable[] run(String MSISDN)
            throws VoltAbortException {
        voltQueueSQL(ShowUserInformation, MSISDN);
        return voltExecuteSQL();
    }
}
