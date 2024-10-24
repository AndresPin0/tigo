package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tigo.aplanchados.config.AuthenticationRequest;
import tigo.aplanchados.config.AuthenticationResponse;
import tigo.aplanchados.config.RegisterRequest;
import tigo.aplanchados.model.User;
import tigo.aplanchados.services.interfaces.JwtService;
import tigo.aplanchados.services.interfaces.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    @Autowired
    private UserService userService;



    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .id(Long.parseLong(request.getUsername()) )
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userService.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = userService.findByUserByUsername(request.getUsername()).orElseThrow();
        var token = jwtService.generateToken((UserDetails)user);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();

    }


}
