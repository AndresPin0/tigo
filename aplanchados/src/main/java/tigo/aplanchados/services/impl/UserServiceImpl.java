package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.RoleRepository;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import tigo.aplanchados.model.Role;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User createUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            Role defaultRole = roleRepository.findByName("EMPLOYEE");
            if (defaultRole == null) {
                defaultRole = new Role();
                defaultRole.setName("EMPLOYEE");
                roleRepository.save(defaultRole);
            }
            user.setRole(defaultRole);
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findAll().stream()
                .filter(user -> username.equals(user.getName()))
                .findFirst();
    }

    @Override
    public boolean updateUser(User user){
        User userToUpdate= userRepository.findById(user.getId()).orElse(null);
        if(userToUpdate==null){
            return false;
        }
        userToUpdate.setId(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setRole(user.getRole());
        userRepository.save(userToUpdate);
        return true;
        
    }


    
}
