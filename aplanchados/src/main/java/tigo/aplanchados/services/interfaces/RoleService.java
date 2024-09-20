package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Permission;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleService {

    List<Role> findAllRoles();

    Optional<Role> findRoleById(Long id);

    Role saveRole(Role role);

    void deleteRole(Long id);

    Optional<Role> findRoleByName(String name);

    boolean addPermissionToRole(Role role, Permission permission);

    Map<String,List<String>> getRolesPermissions();

    boolean removePermissionToRole(Role role, Permission permission);

    boolean changeUserRole(User user, Role role);

    boolean deleteUserRole(User user);

    boolean removeRole(String roleToRemove,String replacemenRole);

    boolean updateRoleServices(Map<String,String> roleServices);
}
