package com.arxall.tema.phishme.controller;

import com.arxall.tema.phishme.model.Credentials;
import com.arxall.tema.phishme.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Credentials credentials) {
        boolean areCredentialsValid = this.authService.authenticate(credentials);
        return new ResponseEntity<>(areCredentialsValid, HttpStatus.OK);
    }

}
