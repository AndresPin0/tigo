package tigo.aplanchados.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.impl.UserServiceImpl;
import java.util.Optional;
import tigo.aplanchados.repositories.RoleRepository;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//check
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllUsers() {
        userService.findAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findUserById() {
        User user = new User();
        user.setId(1L);
        user.setPassword(passwordEncoder.encode("password"));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void createUser() {
        
        User user = new User();
        user.setPassword(passwordEncoder.encode("password"));
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.createUser(user);

        verify(userRepository, times(1)).save(user);
        assertNotNull(savedUser);
    }

    @Test
    void deleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void findUserByUsername() {
        User user = new User();
        user.setName("username");
        when(userRepository.findAll()).thenReturn(List.of(user));

        Optional<User> result = userService.findUserByUsername("username");

        assertTrue(result.isPresent());
        assertEquals("username", result.get().getName());
    }
}
