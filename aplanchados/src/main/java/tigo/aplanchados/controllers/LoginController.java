package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import tigo.aplanchados.model.Role;
import java.util.Map;
import org.springframework.stereotype.Controller;



@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestParam Long id, @RequestParam String password) {
        User user = userRepository.findById(id).get();
        if(user != null && passwordEncoder.matches(password, user.getPassword())) {
            Role role = user.getRole();
            Long userId = user.getId();
            return new ResponseEntity<>(Map.of("role", role, "userId", userId),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    
}
