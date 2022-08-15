package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.SubscriberRepository;
import com.eyecell.rest.api.resource.NewSubscriber;
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
    public String addSubscriberOracleDb (NewSubscriber newSubscriber) throws SQLException {
        return subscriberRepository.addSubscriberOracleDb(newSubscriber);

    }

    @GetMapping("/registerVoltDb")
    @ApiOperation(value = "Create new user in VoltDb")
    public String addSubscriberVoltDb(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        return subscriberRepository.addSubscriberVoltDb(newSubscriber);
    }
}
