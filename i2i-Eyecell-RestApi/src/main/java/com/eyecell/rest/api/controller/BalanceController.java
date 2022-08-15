package com.eyecell.rest.api.controller;


import com.eyecell.rest.api.repository.BalanceForUserRepository;
import com.eyecell.rest.api.repository.BalanceRepository;
import com.eyecell.rest.api.resource.Balance;
import com.eyecell.rest.api.resource.BalanceForUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    BalanceRepository balanceRepository = new BalanceRepository();
    @GetMapping
    @ApiOperation(value = "get all Subcriber's balances")
    public List<Balance> getBalances() throws SQLException {
        return balanceRepository.getBalances();
    }


    @GetMapping("Vbalance")
    @ApiOperation(value = "Get balances by telNo")
    public BalanceForUser getBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        BalanceForUserRepository balanceForUserRepository = new BalanceForUserRepository();
        return balanceForUserRepository.getBalanceByMSISDN(MSISDN);
    }
}
