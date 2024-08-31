package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tigo.aplanchados.model.Role;

import java.util.Optional;
import tigo.aplanchados.repositories.RoleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testSaveAndFindById() {
        Role role = new Role();
        role.setName("ADMIN");

        Role savedRole = roleRepository.save(role);

        Optional<Role> foundRole = roleRepository.findById(savedRole.getId());

        assertTrue(foundRole.isPresent());
        assertEquals("ADMIN", foundRole.get().getName());
    }

    @Test
    void testDelete() {
        Role role = new Role();
        role.setName("USER");

        Role savedRole = roleRepository.save(role);
        roleRepository.deleteById(savedRole.getId());

        Optional<Role> foundRole = roleRepository.findById(savedRole.getId());

        assertTrue(foundRole.isEmpty());
    }

}