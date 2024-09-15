package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import tigo.aplanchados.model.Role;
import java.util.Map;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<Object> login(@RequestParam Long id, @RequestParam String password) {
        User user = userRepository.findById(id).get();
        if(user != null && passwordEncoder.matches(password, user.getPasword())) {
            Role role = user.getRole();
            Long userId = user.getId();
            return new ResponseEntity<>(Map.of("role", role, "userId", userId),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    
}
