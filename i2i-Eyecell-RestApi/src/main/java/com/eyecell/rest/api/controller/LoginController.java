package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.repository.CheckLogin;
import com.eyecell.rest.api.repository.LoginRepository;
import com.eyecell.rest.api.resource.Login;
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
    @GetMapping("/login/{MSISDN}/{password}")
    public ResponseEntity loginCheck(@PathVariable String MSISDN,@PathVariable String password) throws SQLException {
            return loginRepository.loginCheck(MSISDN,password);
    }

    @GetMapping("/login1")
    @ApiOperation(value = "votlDb check")
    public ResponseEntity<Login> loginCheck1(Login login) throws SQLException, IOException, ProcCallException {
        return loginRepository.loginCheck1(login);

    }
}
