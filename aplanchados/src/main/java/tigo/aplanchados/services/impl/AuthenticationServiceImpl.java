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
import tigo.aplanchados.services.interfaces.AuthenticationService;
import tigo.aplanchados.services.interfaces.JwtService;
import tigo.aplanchados.services.interfaces.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
        @Autowired
        private UserService userService;

        private final PasswordEncoder passwordEncoder;

        private final JwtService jwtService;

        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                if (request.getId() == null || request.getPassword() == null ||
                                request.getName() == null || request.getLastName() == null) {
                        return AuthenticationResponse.builder()
                                        .accessToken("Request is incomplete. Missing required fields.")
                                        .build();
                }

                User user = User.builder()
                                .id(Long.parseLong(request.getId()))
                                .password(passwordEncoder.encode(request.getPassword()))
                                .name(request.getName())
                                .lastName(request.getLastName())
                                .build();
                if (userService.findUserById(user.getId()).isPresent()) {
                        System.out.println("User already exists");
                        return AuthenticationResponse.builder()
                                        .accessToken("User already exists")
                                        .build();
                }
                userService.save(user);
                var token = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(token)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getId().toString(),
                                                request.getPassword()));
                User user = userService.findUserById(Long.parseLong(request.getId())).orElseThrow();
                var token = jwtService.generateToken((UserDetails) user);
                System.out.println("User authenticated");
                System.out.println("Authrorities: " + user.getAuthorities().stream().map(Object::toString).toList());

                return AuthenticationResponse.builder()
                                .accessToken(token)          
                                .build();

        }

}
