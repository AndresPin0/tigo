package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.Permission;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.RolePermission;
import tigo.aplanchados.model.RolePermissionPK;
import tigo.aplanchados.repositories.PermissionRepository;
import tigo.aplanchados.repositories.RolePermissionRepository;
import tigo.aplanchados.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class RolePermissionRepositoryTest {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    void testSaveAndFindById() {


        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("SAVE USER");

        permissionRepository.save(permission);

        Role role = new Role();
        role.setName("test");
        roleRepository.save(role);

        RolePermissionPK rolePermissionPK = new RolePermissionPK();
        rolePermissionPK.setRole(role);
        rolePermissionPK.setPermission(permission);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionPK(rolePermissionPK);
        rolePermissionRepository.save(rolePermission);

        if (rolePermissionRepository.findById(rolePermission.getRolePermissionPK()).isEmpty()) {
            fail();
        }
    }

    @Test
    void testDelete() {
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("SAVE USER");

        permissionRepository.save(permission);

        Role role = new Role();
        role.setName("test");
        roleRepository.save(role);

        RolePermissionPK rolePermissionPK = new RolePermissionPK();
        rolePermissionPK.setRole(role);
        rolePermissionPK.setPermission(permission);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionPK(rolePermissionPK);
        rolePermissionRepository.save(rolePermission);

        if (rolePermissionRepository.findById(rolePermission.getRolePermissionPK()).isEmpty()) {
            fail();
        }

        rolePermissionRepository.deleteById(rolePermission.getRolePermissionPK());
        if (rolePermissionRepository.findById(rolePermission.getRolePermissionPK()).isPresent()) {
            fail();
        }
    }
}
