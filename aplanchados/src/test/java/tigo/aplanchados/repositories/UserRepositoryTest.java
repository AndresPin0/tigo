package tigo.aplanchados.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//check

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void testSaveAndFindById() {
        User user = new User();
        user.setName("ProUser");
        user.setLastName("ProLastName");
        user.setId(1L);
        user.setPassword(passwordEncoder.encode("1234"));
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("ProUser", foundUser.get().getName());
    }

    @Test
    void testDelete() {
        User user = new User();
        user.setName("Pro2");
        user.setLastName("ProLastName2");
        user.setId(1L);
        user.setPassword(passwordEncoder.encode("1234"));
        User savedUser = userRepository.save(user);
        userRepository.deleteById(savedUser.getId());
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertTrue(foundUser.isEmpty());
    }

}
