package com.i2i.internship.eyecell;

import org.voltdb.*;

public class ShowPackageAmountVoice extends VoltProcedure {

    public final SQLStmt showVoiceAmount = new SQLStmt(
            "SELECT (SELECT AMOUNT_VOICE FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_VOICE \n" +
                    " FROM BALANCE WHERE SUBSC_ID =?;");

    public VoltTable[] run( int  SUBSC_ID)
            throws VoltAbortException {
        voltQueueSQL(showVoiceAmount,SUBSC_ID);

        return voltExecuteSQL();
    }

}


