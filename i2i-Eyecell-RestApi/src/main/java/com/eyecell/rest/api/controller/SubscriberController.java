package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.SubscriberRepository;
import com.eyecell.rest.api.resource.NewSubscriber;
import com.eyecell.rest.api.resource.Subscriber;
import io.swagger.annotations.ApiOperation;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/subs")
@CrossOrigin
public class SubscriberController {
    SubscriberRepository subscriberRepository = new SubscriberRepository();

    @GetMapping(value = "/getsubs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Users")
    public List<Subscriber> getSubscribers() throws SQLException {
        return subscriberRepository.getSubscribers();
    }

    @GetMapping(value = "/getSubInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Sub's info by MSISDN")
    public List<Subscriber> getSubscriber(String MSISDN) throws IOException, ProcCallException {
        return subscriberRepository.getSubscriber(MSISDN);
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "Create new user")
    public NewSubscriber addSubscriber(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        subscriberRepository.addSubscriberVoltDb(newSubscriber);
        subscriberRepository.addSubscriberOracleDb(newSubscriber);

        return newSubscriber;
    }

    @PostMapping("/web/register")

    @PostMapping(value = "/b/register")
    @ApiOperation(value = "Create new user in Body")
    public NewSubscriber addSubscriberBody(@RequestBody NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        subscriberRepository.addSubscriberVoltDb(newSubscriber);
        subscriberRepository.addSubscriberOracleDb(newSubscriber);

        return newSubscriber;
    }
}
