package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.SubscriberRepository;
import com.eyecell.rest.api.resource.Subscriber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/subs")
public class SubscriberController {
    SubscriberRepository subscriberRepository = new SubscriberRepository();

    @GetMapping("/getsubs")
    @ApiOperation(value = "List all Users")
    public List<Subscriber> getSubscribers() throws SQLException {

        return subscriberRepository.getSubscribers();
    }

    @GetMapping("/registerOracle")
    @ApiOperation(value = "Create new user in OracleDb")
    public String addSubscriberOracleDb (long telNo, String name, String surname, String email, String _password) throws SQLException {
        return subscriberRepository.addSubscriberOracleDb(telNo, name, surname, email, _password);

    }

    @GetMapping("/registerVoltDb")
    @ApiOperation(value = "Create new user in VoltDb")
    public String addSubscriberVoltDb(String MSISDN, String name, String surname, String email, String password,int packageId) throws SQLException, IOException, ProcCallException {
        return subscriberRepository.addSubscriberVoltDb(MSISDN, name, surname, email, password,packageId);
    }
}
