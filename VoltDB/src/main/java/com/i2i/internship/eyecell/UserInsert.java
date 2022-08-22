package com.i2i.internship.eyecell;

import org.voltdb.*;

public class UserInsert extends VoltProcedure {

    public final SQLStmt insert = new SQLStmt(
            " INSERT INTO SUBSCRIBER  (SUBSC_ID ,MSISDN ,NAME,SURNAME ,EMAIL,PASSWORD,SECURITY_QUESTION )VALUES (?,?,?,?,?,?,?);");

    public final SQLStmt balanceInsert = new SQLStmt(
            " INSERT INTO BALANCE (SUBSC_ID ,PACKAGE_ID ) VALUES (?,?);" );

    public VoltTable[] run(int SUBSC_ID,String MSISDN,String NAME,String SURNAME,String EMAIL,String PASSWORD,String SECURITY_QUESTION, int PACKAGE_ID
                           )
            throws VoltAbortException {
        voltQueueSQL(insert, SUBSC_ID,MSISDN,NAME,SURNAME,EMAIL,PASSWORD,SECURITY_QUESTION);
        voltQueueSQL(balanceInsert, SUBSC_ID,PACKAGE_ID);
        return voltExecuteSQL();

    }
}


