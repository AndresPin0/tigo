package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tigo.aplanchados.model.Permission;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import tigo.aplanchados.repositories.PermissionRepository;

@SpringBootTest
class PermissionRepositoryTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    void testSaveAndFindById() {
        Permission permission = new Permission();
        permission.setName("WRITE");

        Permission savedPermission = permissionRepository.save(permission);

        Optional<Permission> foundPermission = permissionRepository.findById(savedPermission.getId());

        assertTrue(foundPermission.isPresent());
        assertEquals("WRITE", foundPermission.get().getName());
    }

    @Test
    void testDelete() {
        Permission permission = new Permission();
        permission.setName("DELETE");

        Permission savedPermission = permissionRepository.save(permission);
        permissionRepository.deleteById(savedPermission.getId());

        Optional<Permission> foundPermission = permissionRepository.findById(savedPermission.getId());

        assertTrue(foundPermission.isEmpty());
    }
}
