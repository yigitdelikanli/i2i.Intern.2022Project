package com.i2i.internship.eyecell;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class getPackage extends VoltProcedure {

    public final SQLStmt getPackageQuery  = new SQLStmt("select package.*  from package, balance, subscriber where package.package_id = balance.package_id and balance.subsc_id = subscriber.subsc_id and subscriber.msisdn = ?;");

    public VoltTable[] run(String MSISDN)
            throws VoltAbortException {
        voltQueueSQL(getPackageQuery, MSISDN);
        return voltExecuteSQL();
    }

}

