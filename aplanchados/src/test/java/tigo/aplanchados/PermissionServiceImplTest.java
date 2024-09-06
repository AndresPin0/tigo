package tigo.aplanchados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.Permission;
import tigo.aplanchados.repositories.PermissionRepository;
import tigo.aplanchados.services.impl.PermissionServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllPermissions() {
        permissionService.findAllPermissions();
        verify(permissionRepository, times(1)).findAll();
    }

    @Test
    void findPermissionById() {
        Permission permission = new Permission();
        permission.setId(1L);
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));

        Optional<Permission> result = permissionService.findPermissionById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void savePermission() {
        Permission permission = new Permission();
        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission savedPermission = permissionService.savePermission(permission);

        verify(permissionRepository, times(1)).save(permission);
        assertNotNull(savedPermission);
    }

    @Test
    void deletePermission() {
        Long permissionId = 1L;
        doNothing().when(permissionRepository).deleteById(permissionId);

        permissionService.deletePermission(permissionId);

        verify(permissionRepository, times(1)).deleteById(permissionId);
    }

    @Test
    void findPermissionByName() {
        Permission permission = new Permission();
        permission.setName("READ");
        when(permissionRepository.findAll()).thenReturn(List.of(permission));

        Optional<Permission> result = permissionService.findPermissionByName("READ");

        assertTrue(result.isPresent());
        assertEquals("READ", result.get().getName());
    }
}