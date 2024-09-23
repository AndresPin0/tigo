package tigo.aplanchados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import tigo.aplanchados.repositories.UserRepository;

@Controller
@RequestMapping("/login")
@PreAuthorize("permitAll()")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping()
    public String loginPage() {
        return "login"; 
    }

    // LoginRequest class to handle the incoming JSON data
    public static class LoginRequest {
        private Long id;
        private String password;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
