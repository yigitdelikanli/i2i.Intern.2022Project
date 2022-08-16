package com.i2isystem.app;

import org.voltdb.*;

public class UserInsert extends VoltProcedure {

    public final SQLStmt insert = new SQLStmt(
            " INSERT INTO SUBSCRIBER  (SUBSC_ID ,MSISDN ,NAME,SURNAME ,EMAIL,PASSWORD )VALUES (?,?,?,?,?,?);");

    public final SQLStmt balanceInsert = new SQLStmt(
            " INSERT INTO BALANCE (SUBSC_ID ,PACKAGE_ID ) VALUES (?,?);" );

    public VoltTable[] run(int SUBSC_ID,String MSISDN,String NAME,String SURNAME,String EMAIL,String PASSWORD, int PACKAGE_ID)
            throws VoltAbortException {
        voltQueueSQL(insert, SUBSC_ID,MSISDN,NAME,SURNAME,EMAIL,PASSWORD);
        voltQueueSQL(balanceInsert, SUBSC_ID,PACKAGE_ID);
        return voltExecuteSQL();

    }
}


