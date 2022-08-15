package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.repository.LoginRepository;
import com.eyecell.rest.api.resource.Subscriber;
import io.swagger.annotations.ApiOperation;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.voltdb.client.ProcCallException;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

@RestController()
public class LoginController {

    LoginRepository loginRepository = new LoginRepository();
    @GetMapping("/login")
    public Integer loginCheck(long telNo, String password) throws SQLException {
            return loginRepository.loginCheck(telNo, password);
    }

    @GetMapping("/login1")
    @ApiOperation(value = "votlDb check")
    public Integer loginCheck1(String telNo, String password) throws SQLException, IOException, ProcCallException {
        return loginRepository.loginCheck1(telNo,password);

    }
}
