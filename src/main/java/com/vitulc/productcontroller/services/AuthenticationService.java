package com.vitulc.productcontroller.services;

import com.vitulc.productcontroller.configs.TokenService;
import com.vitulc.productcontroller.dtos.AuthenticationRecordDto;
import com.vitulc.productcontroller.dtos.LoginResponseRecordDto;
import com.vitulc.productcontroller.dtos.RegisterRecordDto;
import com.vitulc.productcontroller.models.UserModel;
import com.vitulc.productcontroller.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Transactional
    public ResponseEntity<Object> registerUser(RegisterRecordDto newUserData) {

        var encryptedPassword = tokenService.passwordEncoder().encode(newUserData.password());
        var newUser = new UserModel(newUserData, encryptedPassword);

        this.userRepository.save(newUser);
        return ResponseEntity.ok().body("SUCCESSFULLY REGISTERED USER");
    }

    @Transactional
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationRecordDto authenticationData) {

        var user = this.userRepository.findByUsername(authenticationData.username());
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseRecordDto(token));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
