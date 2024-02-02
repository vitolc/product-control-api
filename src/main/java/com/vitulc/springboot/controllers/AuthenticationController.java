package com.vitulc.springboot.controllers;

import com.vitulc.springboot.configs.TokenService;
import com.vitulc.springboot.dtos.AuthenticationRecordDto;
import com.vitulc.springboot.dtos.LoginResponseRecordDto;
import com.vitulc.springboot.dtos.RegisterRecordDto;
import com.vitulc.springboot.models.UserModel;
import com.vitulc.springboot.repositories.UserRepository;
import com.vitulc.springboot.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController (AuthenticationService authenticationService){

        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationRecordDto authenticationData) {
       return authenticationService.login(authenticationData);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRecordDto newUserData) {
        return authenticationService.registerUser(newUserData);
    }
}
