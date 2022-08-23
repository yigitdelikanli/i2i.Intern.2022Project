package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.BalanceRepository;
import com.eyecell.rest.api.resource.RemainingBalanceForUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/balance")
@CrossOrigin
public class BalanceController {
    BalanceRepository balanceRepository = new BalanceRepository();

    @ApiOperation(value = "Get remaining balance by MSISDN in List")
    @GetMapping(value = "balanceByMSISDNinList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RemainingBalanceForUser> getBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        BalanceRepository balanceForUserRepository = new BalanceRepository();
        return balanceForUserRepository.getBalanceByMSISDNinList(MSISDN);
    }

    @ApiOperation(value = "Get remaining balance by MSISDN in Object")
    @GetMapping(value = "balanceByMSISDN", produces = MediaType.APPLICATION_JSON_VALUE)
    public RemainingBalanceForUser getBalanceForUserInObject(String MSISDN) throws IOException, ProcCallException {
        BalanceRepository balanceForUserRepository = new BalanceRepository();
        return balanceForUserRepository.getBalanceByMSISDNInObject(MSISDN);
    }




    /**
     *                    ----- Depreciated -----
     *
     @ApiOperation(value = "get all Subcriber's balances")
     @GetMapping(value = "AllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
     public List<TotalBalanceForAllUsers> getBalances() throws SQLException {
     return balanceRepository.getBalances();
     }
     */


    /**
     *                     ----- Better method exists -----
     *
     @ApiOperation(value = "Get total balance by MSISDN")
     @GetMapping(value = "totalBalanceByMSISDN", produces = MediaType.APPLICATION_JSON_VALUE)
     public TotalBalanceForUser totalBalanceForUser(String MSISDN) throws IOException, ProcCallException {
     BalanceRepository totalBalanceForUserRepository = new BalanceRepository();
     return totalBalanceForUserRepository.totalBalanceForUser(MSISDN);
     }
     */
}
