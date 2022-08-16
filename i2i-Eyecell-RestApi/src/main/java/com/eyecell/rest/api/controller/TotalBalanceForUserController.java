package com.eyecell.rest.api.controller;


import com.eyecell.rest.api.repository.TotalBalanceForUserRepository;
import com.eyecell.rest.api.resource.TotalBalanceForUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voltdb.client.ProcCallException;

import java.io.IOException;

@RestController

public class TotalBalanceForUserController {

    @GetMapping("/TotalBalance")
    public TotalBalanceForUser totalBalanceForUser (String MSISDN) throws IOException, ProcCallException {
        TotalBalanceForUserRepository totalBalanceForUserRepository = new TotalBalanceForUserRepository();

        return totalBalanceForUserRepository.totalBalanceForUser(MSISDN);


    }
}
