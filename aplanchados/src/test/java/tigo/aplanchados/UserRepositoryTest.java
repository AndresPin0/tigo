package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tigo.aplanchados.model.User;

import java.util.Optional;
import tigo.aplanchados.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindById() {
        User user = new User();
        user.setName("ProUser");

        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("ProUser", foundUser.get().getName());
    }

    @Test
    void testDelete() {
        User user = new User();
        user.setName("Pro2");

        User savedUser = userRepository.save(user);
        userRepository.deleteById(savedUser.getId());

        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertTrue(foundUser.isEmpty());
    }
}
