package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.BalanceRepository;
import com.eyecell.rest.api.resource.TotalBalanceForAllUsers;
import com.eyecell.rest.api.resource.RemainingBalanceForUser;
import com.eyecell.rest.api.resource.TotalBalanceForUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/balance")
@CrossOrigin
public class BalanceController {
    BalanceRepository balanceRepository = new BalanceRepository();

    @GetMapping(value = "AllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all Subcriber's balances")
    public List<TotalBalanceForAllUsers> getBalances() throws SQLException {
        return balanceRepository.getBalances();
    }

    @GetMapping(value = "sBalance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get balances by MSISDN")
    public RemainingBalanceForUser getBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        BalanceRepository balanceForUserRepository = new BalanceRepository();
        return balanceForUserRepository.getBalanceByMSISDN(MSISDN);
    }

    @ApiOperation(value = "totalBalance")
    @GetMapping(value = "/TotalBalance", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalBalanceForUser totalBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        BalanceRepository totalBalanceForUserRepository = new BalanceRepository();
        return totalBalanceForUserRepository.totalBalanceForUser(MSISDN);
    }
}
