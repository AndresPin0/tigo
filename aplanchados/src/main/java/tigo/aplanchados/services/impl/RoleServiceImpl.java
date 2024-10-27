package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tigo.aplanchados.model.Permission;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.RolePermission;
import tigo.aplanchados.model.RolePermissionPK;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.PermissionRepository;
import tigo.aplanchados.repositories.RolePermissionRepository;
import tigo.aplanchados.repositories.RoleRepository;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.RoleService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role saveRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            return null;
        }

        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findAll().stream()
                .filter(role -> name.equals(role.getName()))
                .findFirst();
    }

    @Override
    public boolean removeRole(String roleToRemove, String replacementRole) {
        Role foundRoleToRemove = roleRepository.findByName(roleToRemove);
        Role foundReplacementRole = roleRepository.findByName(replacementRole);

        if (roleToRemove.equals(replacementRole)) {
            return false;
        }

        if (roleRepository.count() == 1) {
            return false;
        }

        if (foundReplacementRole == null || foundRoleToRemove == null) {
            return false;
        }

        List<User> usersWithRoleToRemove = userRepository.findAllByRole(foundRoleToRemove);
        for (User user : usersWithRoleToRemove) {
            user.setRole(foundReplacementRole);
            userRepository.save(user);
        }

        List<RolePermission> rolePermissionsToRemove = rolePermissionRepository.findAllByRole(foundRoleToRemove);
        for (RolePermission rolePermission : rolePermissionsToRemove) {
            rolePermissionRepository.delete(rolePermission); 
        }

        roleRepository.delete(foundRoleToRemove);

        return true; 
    }

    @Override
    public boolean addPermissionToRole(Role role, Permission permission) {
        Role foundRole = roleRepository.findByName(role.getName());
        Permission foundPermission = permissionRepository.findByName(permission.getName());

        if (foundRole == null) {
            System.out.println("ROLE NOT FOUND");
            return false;
        }
        if (foundPermission == null) {
            System.out.println("PERMISSION NOT FOUND");
            return false;
        }

        if (rolePermissionRepository.findByRoleAndPermission(foundRole, foundPermission) != null) {
            System.out.println("-------");
            System.out.println(foundRole.getName());
            System.out.println(foundPermission.getName());
            System.out.println("PERMISSION ALREADY EXISTS");
            return false;
        }

        RolePermissionPK rolePermissionPK = new RolePermissionPK();
        rolePermissionPK.setRole(foundRole);
        rolePermissionPK.setPermission(foundPermission);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionPK(rolePermissionPK);

        rolePermissionRepository.save(rolePermission);
        return true;
    }

    @Override
    public boolean removePermissionToRole(Role role, Permission permission) {
        Role foundRole = roleRepository.findByName(role.getName());
        Permission foundPermission = permissionRepository.findByName(permission.getName());

        if (foundRole == null) {
            System.out.println("ROLE NOT FOUND");
            return false;
        }
        if (foundPermission == null) {
            System.out.println("PERMISSION NOT FOUND");
            return false;
        }
        if (rolePermissionRepository.findByRoleAndPermission(foundRole, foundPermission) == null) {
        
            return false;
        }
        rolePermissionRepository.deleteById(
                rolePermissionRepository.findByRoleAndPermission(foundRole, foundPermission).getRolePermissionPK());
        return true;

    }

    @Override
    public boolean changeUserRole(User user, Role role) {
        if (userRepository.findById(user.getId()).isEmpty()) {
            return false;
        }
        if (roleRepository.findByName(role.getName()) == null) {
            return false;
        }
        if (user.getRole().getName().equals(role.getName())) {
            return false;
        }

        user.setRole(roleRepository.findByName(role.getName()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUserRole(User givenUser) {
        if (userRepository.findById(givenUser.getId()).isEmpty()) {
            return false;
        }
        User user = userRepository.findById(givenUser.getId()).get();
        user.setRole(roleRepository.findByName("EMPLOYEE"));
        userRepository.save(user);
        return true;
    }

    @Override
    public Map<String, List<String>> getRolesPermissions() {
        Map<String, List<String>> map = new HashMap<>();

        List<Role> roles = roleRepository.findAll();

        for (Role role : roles) {
            map.put(role.getName(), role.getRolePermissions().stream()
                    .map(rolePemission -> rolePemission.getPermission().getName()).toList());
        }

        return map;

    }

    @Override
    //receives a map whose keys are the role name and the permissions that the role must have
    //if the id exists the permission is added otherwise it is removed
    public boolean updateRoleServices(Map<String, String> roleServices) {

        String foundRoleName = null;
        Role role = null;

        foundRoleName = roleServices.get("roleName");

        if (foundRoleName == null) {
            return false;
        }
        if (!roleRepository.existsByName(foundRoleName)) {
            return false;
        }

        Set<String> roleServicesSet = new HashSet<>();

        for (Map.Entry<String, String> entry : roleServices.entrySet()) {
            System.out.println(entry.getKey());
            if (entry.getKey().equals("roleName")) {
                continue;
            }
            System.out.println("HOLA");
            roleServicesSet.add(entry.getKey());
        }
        role = new Role();
        role.setName(foundRoleName);
        System.out.println("NOMBRE DEL ROL");
        System.out.println(role.getName());
        for (Permission permission : permissionRepository.findAll()) {
            
            System.out.println(permission.getName());
            String permissionName = permission.getName();
            if (roleServicesSet.contains(permissionName)) {
                System.out.println("TIENE EL PERMISO");
                if (addPermissionToRole(role, permission)) {
                    System.out.println("PERMISO AGREGADO");
                }
            } else {
                if (removePermissionToRole(role, permissionRepository.findByName(permissionName))) {
                    System.out.println("PERMISO REMOVIDO");
                }
            }

        }

        return true;
    }

}
