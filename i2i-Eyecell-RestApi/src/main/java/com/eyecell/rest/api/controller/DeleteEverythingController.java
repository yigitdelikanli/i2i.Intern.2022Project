/*
package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.DeleteEverything;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@CrossOrigin
@RequestMapping("/deleteAll")
public class DeleteEverythingController {
    DeleteEverything deleteEverything = new DeleteEverything();


    @GetMapping
    @ApiOperation("Delete everything from VoltDb,HazelCast,OracleDb")
    public ResponseEntity deleteEverything() throws SQLException {
        deleteEverything.deleteMap();
        deleteEverything.deleteVoltDb();
        deleteEverything.deleteOracleDb();

        return new ResponseEntity<>("All Datas are deleted", HttpStatus.OK);
    }
}
*/
