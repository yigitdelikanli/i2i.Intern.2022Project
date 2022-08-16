package com.i2isystem.app;

import org.voltdb.*;

public class ShowPackageAmountVoice extends VoltProcedure {

    public final SQLStmt showVoiceAmount = new SQLStmt(
            "SELECT (SELECT AMOUNT_VOICE FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_VOICE \n" +
                    " FROM BALANCE WHERE SUBSC_ID =(SELECT SUBSC_ID FROM  SUBSCRIBER WHERE MSISDN =?);");

    public VoltTable[] run( String MSISDN)
            throws VoltAbortException {
        voltQueueSQL(showVoiceAmount,MSISDN);

        return voltExecuteSQL();
    }

}
