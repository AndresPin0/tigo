package tigo.aplanchados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tigo.aplanchados.config.AuthenticationRequest;
import tigo.aplanchados.config.AuthenticationResponse;
import tigo.aplanchados.config.RegisterRequest;
import tigo.aplanchados.services.interfaces.AuthenticationService;
import tigo.aplanchados.services.interfaces.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationService service;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        if (request.getId() == null || request.getPassword() == null || request.getName() == null
                || request.getLastName() == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            if (userService.findUserById(Long.parseLong(request.getId())).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
