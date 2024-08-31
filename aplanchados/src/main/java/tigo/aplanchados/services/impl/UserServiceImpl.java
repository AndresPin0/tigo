package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }



    @Override
    public User saveUser(User user) {
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
}
