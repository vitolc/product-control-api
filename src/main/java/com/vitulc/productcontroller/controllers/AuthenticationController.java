package com.vitulc.productcontroller.controllers;

import com.vitulc.productcontroller.dtos.AuthenticationRecordDto;
import com.vitulc.productcontroller.dtos.RegisterResponseRecordDto;
import com.vitulc.productcontroller.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationRecordDto authenticationRecordDto) {
        return authenticationService.login(authenticationRecordDto);
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> register(@RequestBody RegisterResponseRecordDto registerResponseRecordDto) {
        return authenticationService.registerUser(registerResponseRecordDto);
    }

    @DeleteMapping("/delete/{username}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        return authenticationService.deleteUser(username);
    }
}
