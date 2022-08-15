package com.eyecell.rest.api.controller;


import com.eyecell.rest.api.dbhelper.DbHelper;
import com.eyecell.rest.api.repository.PackageRepository;
import com.eyecell.rest.api.resource.Package;
import com.eyecell.rest.api.resource.Subscriber;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {


    PackageRepository packageRepository = new PackageRepository();

    @GetMapping("/getpackages")
    @ApiOperation(value = "List all packages")
    public List<Package> packageList () throws SQLException {
        return packageRepository.packageList();
    }

    @GetMapping("/addpackage")
    @ApiOperation(value = "Add a package")
    public String aPackage (String packageName, long amountVoice,long amountInternet,long amountSMS,long duration) throws SQLException {
        return packageRepository.aPackage(packageName, amountVoice, amountInternet, amountSMS, duration);
    }

}
