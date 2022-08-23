package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.LoginRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController("/login")
@CrossOrigin
public class LoginController {
    LoginRepository loginRepository = new LoginRepository();

    @GetMapping(value = "/{MSISDN}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginCheck(@PathVariable String MSISDN, @PathVariable String password) throws SQLException {
        return loginRepository.loginCheck(MSISDN, password);
    }

    @GetMapping(value = "/a/{MSISDN}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginCheckAndroid(@PathVariable String MSISDN, @PathVariable String password) throws SQLException {
        return loginRepository.loginCheckAndroid(MSISDN, password);
    }

    /**
     *
     *                              ------ CheckUser in VoltDB ------- (Depreciated)

     @GetMapping("/login1")
     @ApiOperation(value = "votlDb check")
     public ResponseEntity<Login> loginCheck1(Login login) throws SQLException, IOException, ProcCallException {
     return loginRepository.loginCheck1(login);

     }
     **/
}
