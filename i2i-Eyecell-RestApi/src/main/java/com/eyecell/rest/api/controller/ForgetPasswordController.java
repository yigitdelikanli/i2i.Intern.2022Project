package com.eyecell.rest.api.controller;


import com.eyecell.rest.api.repository.ForgotPasswordRepository;
import com.eyecell.rest.api.resource.ForgotPassword;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/forgotp")
public class ForgetPasswordController {

    ForgotPasswordRepository forgotPasswordRepository;

    @PostMapping
    @ApiOperation(value = "Mails you if correct")
    public ResponseEntity forgotPassword(ForgotPassword forgotPassword) throws SQLException {
        forgotPasswordRepository = new ForgotPasswordRepository();
        return forgotPasswordRepository.forgotPassword(forgotPassword);
    }
}
