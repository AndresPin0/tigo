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
import org.springframework.stereotype.Controller;
import java.util.Optional;

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
    public String login(@RequestParam Long id, @RequestParam String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("User authenticated successfully.");
                return "redirect:/dashboard";
            } else {
                System.out.println("Password does not match.");
            }
        } else {
            System.out.println("User not found.");
        }
        return "redirect:/login?error";
    }

}
