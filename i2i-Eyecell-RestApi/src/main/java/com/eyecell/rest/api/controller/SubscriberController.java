package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.SubscriberRepository;
import com.eyecell.rest.api.resource.NewSubscriber;
import com.eyecell.rest.api.resource.Subscriber;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/getsubs")
    @ApiOperation(value = "List all Users")
    public List<Subscriber> getSubscribers() throws SQLException {
        return subscriberRepository.getSubscribers();
    }

    @GetMapping("/register")
    @ApiOperation(value = "Create new user")
    public NewSubscriber addSubscriber(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        subscriberRepository.addSubscriberOracleDb(newSubscriber);
        subscriberRepository.addSubscriberVoltDb(newSubscriber);
        return newSubscriber;
    }
}
