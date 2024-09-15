package tigo.aplanchados.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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