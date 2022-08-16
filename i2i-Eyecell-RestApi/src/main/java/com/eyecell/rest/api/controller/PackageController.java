package com.eyecell.rest.api.controller;


import com.eyecell.rest.api.repository.PackageRepository;
import com.eyecell.rest.api.resource.Package;
import com.eyecell.rest.api.resource.PackageList;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/package")
@CrossOrigin
public class PackageController {


    PackageRepository packageRepository = new PackageRepository();

    @GetMapping(value = "/getpackages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all packages")
    public List<Package> packageList () throws SQLException {
        return packageRepository.packageList();
    }

    @GetMapping(value = "/packageIdName", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lists package's ID and Name")
    public List<PackageList> packageLists () throws SQLException {
        return packageRepository.packageLists();
    }

}
