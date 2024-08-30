package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAllRoles();

    Optional<Role> findRoleById(Long id);

    Role saveRole(Role role);

    void deleteRole(Long id);

    Optional<Role> findRoleByName(String name);
}
