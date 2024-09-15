package tigo.aplanchados.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.Permission;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
//check
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
