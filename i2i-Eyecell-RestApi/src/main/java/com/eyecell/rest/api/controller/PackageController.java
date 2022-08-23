package com.eyecell.rest.api.controller;

import com.eyecell.rest.api.repository.PackageRepository;
import com.eyecell.rest.api.resource.Package;
import com.eyecell.rest.api.resource.PackageList;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/package")
@CrossOrigin
public class PackageController {
    PackageRepository packageRepository = new PackageRepository();

    @GetMapping(value = "/getpackages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all packages")
    public List<Package> packageList() throws SQLException {
        return packageRepository.packageList();
    }

    @GetMapping(value = "/packageIdName", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lists package's ID and Name")
    public List<PackageList> packageLists() throws SQLException {
        return packageRepository.packageLists();
    }

    @GetMapping(value = "/packageInfoInList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = " Bring User's package info in List")
    public List<Package> packageInfoInList(String MSISDN) throws IOException, ProcCallException {
        return packageRepository.getPackageByMSISDNinList(MSISDN);
    }

    @GetMapping(value = "/packageInfoInObject", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = " Bring User's package info in Object")
    public Package packageInfoInObject(String MSISDN) throws IOException, ProcCallException {
        return packageRepository.getPackageByMSISDNinObject(MSISDN);
    }

}
