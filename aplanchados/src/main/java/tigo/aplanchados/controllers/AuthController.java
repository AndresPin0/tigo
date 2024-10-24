package tigo.aplanchados.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.impl.AuthenticationService;

import lombok.RequiredArgsConstructor;
import tigo.aplanchados.config.AuthenticationRequest;
import tigo.aplanchados.config.AuthenticationResponse;
import tigo.aplanchados.config.RegisterRequest;


@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
