package tigo.aplanchados.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import tigo.aplanchados.model.User;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import tigo.aplanchados.services.interfaces.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/create")
    @PreAuthorize("permitAll()")
    public String registerPage() {
        return "register";
    }
    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Creating user with password: " + user.getPassword());
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
        
        
    }

    @GetMapping

    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(Long id) {
        return ResponseEntity.ok(userService.findUserById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    
}
