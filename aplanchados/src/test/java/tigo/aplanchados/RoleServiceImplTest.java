package tigo.aplanchados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.repositories.RoleRepository;
import tigo.aplanchados.services.impl.RoleServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
//check
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllRoles() {
        roleService.findAllRoles();
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void findRoleById() {
        Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.findRoleById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void saveRole() {
        Role role = new Role();
        when(roleRepository.save(role)).thenReturn(role);

        Role savedRole = roleService.saveRole(role);

        verify(roleRepository, times(1)).save(role);
        assertNotNull(savedRole);
    }

    @Test
    void deleteRole() {
        Long roleId = 1L;
        doNothing().when(roleRepository).deleteById(roleId);

        roleService.deleteRole(roleId);

        verify(roleRepository, times(1)).deleteById(roleId);
    }
}
